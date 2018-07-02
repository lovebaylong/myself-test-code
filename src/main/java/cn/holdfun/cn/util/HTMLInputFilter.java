package cn.holdfun.cn.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HTMLInputFilter
{
    private static final Log log = LogFactory.getLog(HTMLInputFilter.class);
    
    /**
     * flag determining whether to try to make tags when presented with
     * "unbalanced" angle brackets (e.g. "<b text </b>" becomes "<b> text
     * </b>"). If set to false, unbalanced angle brackets will be html escaped.
     */
    protected static final boolean ALWAYS_MAKE_TAGS = true;

    /**
     * flag determing whether comments are allowed in input String.
     */
    protected static final boolean STRIP_COMMENTS = true;

    /** regex flag union representing /si modifiers in php * */
    protected static final int REGEX_FLAGS_SI = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;

    /**
     * set of allowed html elements, along with allowed attributes for each
     * element *
     */
    protected Map<String, List<String>> vAllowed;

    /** counts of open tags for each (allowable) html element * */
    protected Map<String, Integer> vTagCounts;

    /** html elements which must always be self-closing (e.g. "<img />") * */
    protected String[] vSelfClosingTags;

    /**
     * html elements which must always have separate opening and closing tags
     * (e.g. "<b></b>") *
     */
    protected String[] vNeedClosingTags;

    /** attributes which should be checked for valid protocols * */
    protected String[] vProtocolAtts;

    /** allowed protocols * */
    protected String[] vAllowedProtocols;

    /** allowed protocols * */
    protected String[] unAllowedProtocols;

    /**
     * tags which should be removed if they contain no content (e.g. "<b></b>"
     * or "<b />") *
     */
    protected String[] vRemoveBlanks;

    /** entities allowed within html markup * */
    protected String[] vAllowedEntities;

    protected boolean vDebug;

    private int paramMaxLength = 100;// 参数最大长度，前台门户没有超长度的值需要提交，所以暂定100个字符长度
    
	public HTMLInputFilter() {
		this(100);
	}

    public HTMLInputFilter(int paramMaxLength)
    {
        this(paramMaxLength, false);
    }

    public HTMLInputFilter(int paramMaxLength, boolean debug)
    {
    	this.paramMaxLength = paramMaxLength;
        vDebug = debug;

        vAllowed = new HashMap<String, List<String>>();
        vTagCounts = new HashMap<String, Integer>();

        ArrayList<String> a_atts = new ArrayList<String>();
        a_atts.add("href");
        a_atts.add("target");
        // vAllowed.put( "a", a_atts );

        ArrayList<String> img_atts = new ArrayList<String>();
        img_atts.add("src");
        img_atts.add("width");
        img_atts.add("height");
        img_atts.add("alt");
        // vAllowed.put( "img", img_atts );

        //ArrayList<String> no_atts = new ArrayList<String>();
        // vAllowed.put( "b", no_atts );
        // vAllowed.put( "strong", no_atts );
        // vAllowed.put( "i", no_atts );
        // vAllowed.put( "em", no_atts );

        vSelfClosingTags = new String[] {};
        vNeedClosingTags = new String[] {};
        vAllowedProtocols = new String[] { }; // no ftp.
        unAllowedProtocols = new String[]{"ftp", "mailto", "http", "https"};
        vProtocolAtts = new String[] {};
        vRemoveBlanks = new String[] {};
        vAllowedEntities = new String[] {};

    }

    protected void reset()
    {
        vTagCounts = new HashMap<String, Integer>();
    }

    protected void debug(String msg)
    {
        if (vDebug)
            log.info(msg);
    }

    // ---------------------------------------------------------------
    // my versions of some PHP library functions

    public static String chr(int decimal)
    {
        return String.valueOf((char) decimal);
    }

    public static String htmlSpecialChars(String s)
    {
        s = s.replaceAll("&", "&amp;");
        s = s.replaceAll("\"", "&quot;");
        s = s.replaceAll("<", "&lt;");
        s = s.replaceAll(">", "&gt;");
        return s;
    }

    // ---------------------------------------------------------------

    /**
     * given a user submitted input String, filter out any invalid or restricted
     * html.
     * @param input text (i.e. submitted by a user) than may contain html
     * @return "clean" version of input, with only valid, whitelisted html
     *         elements allowed
     */
    public synchronized String filter(String input)
    {
        reset();
        String s = new String(input);

        s = maxlengthFilter(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("     参数非法maxlengthFilter过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = escapeComments(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("     参数非法escapeComments过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = balanceHTML(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("     参数非法balanceHTML过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = checkTags(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("     参数非法checkTags过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = processRemoveBlanks(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("     参数非法processRemoveBlanks过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = validateEntities(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("    参数非法validateEntites过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = filterOnEvent(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("    参数非法filterOnEvent过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = filterTransactSQLInjection(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("    参数非法filterTransactSQLInjection过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = filterCssInjection(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("    参数非法filterCssInjection过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = filterTabs(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("    参数非法filterTabs过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = filterSqlSpeci(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("    参数非法filterSqlSpeci过滤: " + s);
            debug("************************************************");
            return s;
        }

        s = processParamProtocol(s);
        if (!s.equals(input))
        {
            debug("************************************************");
            debug("     参数: " + input);
            debug("    参数非法processParamProtocol过滤: " + s);
            debug("************************************************");
            return s;
        }

        return s;
    }

    protected String maxlengthFilter(String s)
    {
        if (StringUtils.isNotEmpty(s))
        {
            if (paramMaxLength < s.length())
                return s.substring(0, paramMaxLength);
        }
        return s;
    }

    protected String escapeComments(String s)
    {
        Pattern p = Pattern.compile("<!--(.*?)-->", Pattern.DOTALL);
        Matcher m = p.matcher(s);
        StringBuffer buf = new StringBuffer();
        if (m.find())
        {
            String match = m.group(1); // (.*?)
            m.appendReplacement(buf, "<!--" + htmlSpecialChars(match) + "-->");
        }
        m.appendTail(buf);

        return buf.toString();
    }

    protected String balanceHTML(String s)
    {
        if (ALWAYS_MAKE_TAGS)
        {
            //
            // try and form html
            //
            s = regexReplace("^>", "", s);
            s = regexReplace("<([^>]*?)(?=<|$)", "<$1>", s);
            s = regexReplace("(^|>)([^<]*?)(?=>)", "$1<$2", s);

        }
        else
        {
            //
            // escape stray brackets
            //
            s = regexReplace("<([^>]*?)(?=<|$)", "&lt;$1", s);
            s = regexReplace("(^|>)([^<]*?)(?=>)", "$1$2&gt;<", s);

            //
            // the last regexp causes '<>' entities to appear
            // (we need to do a lookahead assertion so that the last bracket can
            // be used in the next pass of the regexp)
            //
            s = s.replaceAll("<>", "");
        }

        return s;
    }

    protected String filterOnEvent(String s)
    {
        // System.Text.RegularExpressions.Regex regex3 = new
        // System.Text.RegularExpressions.Regex(@"
        // on[\s\S]*=",System.Text.RegularExpressions.RegexOptions.IgnoreCase);
        s = regexReplace(
            "(?i:on(blur|c(hange|lick)|dblclick|focus|keypress|(key|mouse)(down|up)|(un)?load|mouse(move|o(ut|ver))|reset|s(elect|ubmit)))|javascript|alert",
            "", s);
        return s;
    }

    protected String filterTransactSQLInjection(String s)
    {
        s = s.replaceAll(".*([']+|(--)+|[+]+).*", " ");
        return s;
    }

    private static String CSS_EXPRESSION = "expression";

    protected String filterCssInjection(String s)
    {
        String _s = s.toLowerCase();
        if (_s.indexOf(CSS_EXPRESSION) > 0)
        {
            int i = _s.indexOf(CSS_EXPRESSION);
            String a = s.substring(0, i);
            if (a.endsWith(":"))
                a = a.substring(0, a.length() - 1);

            String b = s.substring(i + CSS_EXPRESSION.length());
            return a + b;
        }
        else
        {
            return s;
        }
    }

    protected String checkTags(String s)
    {
        Pattern p = Pattern.compile("<(.*?)>", Pattern.DOTALL);
        Matcher m = p.matcher(s);

        StringBuffer buf = new StringBuffer();
        while (m.find())
        {
            String replaceStr = m.group(1);
            replaceStr = processTag(replaceStr);
            m.appendReplacement(buf, replaceStr);
        }
        m.appendTail(buf);

        s = buf.toString();

        // these get tallied in processTag
        // (remember to reset before subsequent calls to filter method)
        for (String key : vTagCounts.keySet())
        {
            for (int ii = 0; ii < vTagCounts.get(key); ii++)
            {
                s += "</" + key + ">";
            }
        }

        return s;
    }

    protected String processRemoveBlanks(String s)
    {
        for (String tag : vRemoveBlanks)
        {
            s = regexReplace("<" + tag + "(\\s[^>]*)?></" + tag + ">", "", s);
            s = regexReplace("<" + tag + "(\\s[^>]*)?/>", "", s);
        }

        return s;
    }

    protected String regexReplace(String regex_pattern, String replacement, String s)
    {
        Pattern p = Pattern.compile(regex_pattern);
        Matcher m = p.matcher(s);
        return m.replaceAll(replacement);
    }

    protected String regexReplace(String regex_pattern, String replacement, String s, int flag)
    {
        Pattern p = Pattern.compile(regex_pattern, flag);
        Matcher m = p.matcher(s);
        return m.replaceAll(replacement);
    }

    protected String processTag(String s)
    {
        // ending tags
        Pattern p = Pattern.compile("^/([a-z0-9]+)", REGEX_FLAGS_SI);
        Matcher m = p.matcher(s);
        if (m.find())
        {
            String name = m.group(1).toLowerCase();
            if (vAllowed.containsKey(name))
            {
                if (!inArray(name, vSelfClosingTags))
                {
                    if (vTagCounts.containsKey(name))
                    {
                        vTagCounts.put(name, vTagCounts.get(name) - 1);
                        return "</" + name + ">";
                    }
                }
            }
        }

        // starting tags
        p = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", REGEX_FLAGS_SI);
        m = p.matcher(s);
        if (m.find())
        {
            String name = m.group(1).toLowerCase();
            String body = m.group(2);
            String ending = m.group(3);

            // debug( "in a starting tag, name='" + name + "'; body='" + body +
            // "'; ending='" + ending + "'" );
            if (vAllowed.containsKey(name))
            {
                StringBuffer params = new StringBuffer();

                Pattern p2 = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", REGEX_FLAGS_SI);
                Pattern p3 = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", REGEX_FLAGS_SI);
                Matcher m2 = p2.matcher(body);
                Matcher m3 = p3.matcher(body);
                List<String> paramNames = new ArrayList<String>();
                List<String> paramValues = new ArrayList<String>();
                while (m2.find())
                {
                    paramNames.add(m2.group(1)); // ([a-z0-9]+)
                    paramValues.add(m2.group(3)); // (.*?)
                }
                while (m3.find())
                {
                    paramNames.add(m3.group(1)); // ([a-z0-9]+)
                    paramValues.add(m3.group(3)); // ([^\"\\s']+)
                }

                String paramName, paramValue;
                for (int ii = 0; ii < paramNames.size(); ii++)
                {
                    paramName = paramNames.get(ii).toLowerCase();
                    paramValue = paramValues.get(ii);

                    // debug( "paramName='" + paramName + "'" );
                    // debug( "paramValue='" + paramValue + "'" );
                    // debug( "allowed? " + vAllowed.get( name ).contains(
                    // paramName ) );

                    if (vAllowed.get(name).contains(paramName))
                    {
                        if (inArray(paramName, vProtocolAtts))
                        {
                            paramValue = processParamProtocol(paramValue);
                        }
                        params.append(" ").append(paramName).append("=\"").append(paramValue).append("\"");
                    }
                }

                if (inArray(name, vSelfClosingTags))
                {
                    ending = " /";
                }

                if (inArray(name, vNeedClosingTags))
                {
                    ending = "";
                }

                if (ending == null || ending.length() < 1)
                {
                    if (vTagCounts.containsKey(name))
                    {
                        vTagCounts.put(name, vTagCounts.get(name) + 1);
                    }
                    else
                    {
                        vTagCounts.put(name, 1);
                    }
                }
                else
                {
                    ending = " /";
                }
                return "<" + name + params + ending + ">";
            }
            else
            {
                return "";
            }
        }

        // comments
        p = Pattern.compile("^!--(.*)--$", REGEX_FLAGS_SI);
        m = p.matcher(s);
        if (m.find())
        {
            String comment = m.group();
            if (STRIP_COMMENTS)
            {
                return "";
            }
            else
            {
                return "<" + comment + ">";
            }
        }

        return "";
    }

    protected String processParamProtocol(String s)
    {
        //s = decodeEntities(s);
        Pattern p = Pattern.compile("^([^:]+):", REGEX_FLAGS_SI);
        Matcher m = p.matcher(s);
        if (m.find())
        {
            String protocol = m.group(1);
            if (inArray(protocol.toLowerCase(), unAllowedProtocols))
            {
                // bad protocol, turn into local anchor link instead
                s = "#" + s.substring(protocol.length() + 1, s.length());
                if (s.startsWith("#//"))
                    s = "#" + s.substring(3, s.length());
            }
        }

        return s;
    }

    protected String decodeEntities(String s)
    {
        StringBuffer buf = new StringBuffer();

        Pattern p = Pattern.compile("&#(\\d+);?");
        Matcher m = p.matcher(s);
        while (m.find())
        {
            String match = m.group(1);
            int decimal = Integer.decode(match).intValue();
            m.appendReplacement(buf, chr(decimal));
        }
        m.appendTail(buf);
        s = buf.toString();

        buf = new StringBuffer();
        p = Pattern.compile("&#x([0-9a-f]+);?");
        m = p.matcher(s);
        while (m.find())
        {
            String match = m.group(1);
            int decimal = Integer.decode(match).intValue();
            m.appendReplacement(buf, chr(decimal));
        }
        m.appendTail(buf);
        s = buf.toString();

        buf = new StringBuffer();
        p = Pattern.compile("%([0-9a-f]{2});?");
        m = p.matcher(s);
        while (m.find())
        {
            String match = m.group(1);
            int decimal = Integer.decode(match).intValue();
            m.appendReplacement(buf, chr(decimal));
        }
        m.appendTail(buf);
        s = buf.toString();

        s = validateEntities(s);
        return s;
    }

    protected String validateEntities(String s)
    {
        // validate entities throughout the string
        Pattern p = Pattern.compile("&([^&;]*)(?=(;|&))");
        Matcher m = p.matcher(s);
        if (m.find())
        {
            String one = m.group(1); // ([^&;]*)
            String two = m.group(2); // (?=(;|&|$))
            s = checkEntity(one, two);
        }

        // validate quotes outside of tags
        p = Pattern.compile("(>|^)([^<]+?)(<)", Pattern.DOTALL);
        m = p.matcher(s);
        StringBuffer buf = new StringBuffer();
        if (m.find())
        {
            String one = m.group(1); // (>|^)
            String two = m.group(2); // ([^<]+?)
            String three = m.group(3); // (<|$)
            m.appendReplacement(buf, one + two.replaceAll("\"", "&quot;") + three);
        }
        m.appendTail(buf);

        return s;
    }

    protected String checkEntity(String preamble, String term)
    {
        if (!";".equals(term))
        {
            return "&amp;" + preamble;
        }

        if (isValidEntity(preamble))
        {
            return "&" + preamble;
        }

        return "&amp;" + preamble;
    }

    protected boolean isValidEntity(String entity)
    {
        return inArray(entity, vAllowedEntities);
    }

    private boolean inArray(String s, String[] array)
    {
        for (String item : array)
            if (item != null && item.equals(s))
                return true;

        return false;
    }

    protected String filterTabs(String s)
    {
        s = regexReplace("[\\r\\n\\t\\f\\v]", "", s);
        return s;
    }
    
    private String filterSqlSpeci(String s)
    {
        s = regexReplace("%20(and|or)%20", "", s, REGEX_FLAGS_SI);
        s = regexReplace("/\\*\\*/(and|or)/\\*\\*/", "", s, REGEX_FLAGS_SI);
        
        return s.replaceAll("/\\*\\*/", "").replaceAll("/\\*/", "");
    }

    public static void main(String[] args)
    {
        HTMLInputFilter c = new HTMLInputFilter(100,true);
        c.filter("%2FticketBuy.action%3FbuyInfo%3D%25E6%2598%25AF%252C1%252C%25E5%2587%25A4%25E5%25B2%2597%25E5%25A4%25A9%25E6%25A1%25A5%252C2012-09-08%2B16%253A05%252C%25E9%25AB%2598%25E7%25BA%25A7%25E5%25BA%25A7%25E5%25B8%25AD%252C23%25E5%2585%2583%252C11%252C%25E5%25B9%25B3%25E6%25B9%2596%252C%25E5%2587%25A4%25E5%25B2%2597%252C%25E4%25B8%259C%25E8%258E%259E%25E6%25B1%25BD%25E8%25BD%25A6%25E4%25B8%259C%25E7%25AB%2599%252C03408%252C9%26tickStation.startStation%3D%25E4%25B8%259C%25E8%258E%259E%25E6%25B1%25BD%25E8%25BD%25A6%25E4%25B8%259C%25E7%25AB%2599%26tickStation.stationname%3D%25E5%2587%25A4%25E5%25B2%2597%26tickStation.sourceId%3D1%26beginDate%3D2012-09-08%26tickStationCase.isBuy%3D0%26step%3D3%26fromType%3D0%26");
        c.filter("2012-09-08%20OR%20");
        c.filter("2012-09-08%20or%20");
        c.filter("2012-09-08%20Or%20");
        c.filter("2012-09-08%20oR%20");
        c.filter("2012-09-08/**/and/**/");
        c.filter("2012-09-08/**/And/**/");
        c.filter("2012-09-08/**/aNd/**/");
        c.filter("2012-09-08/**/anD/**/");
        c.filter("preUrl=%2FentryCountCentry.action%3FreturnUrl%3DshoppingCart.action%26allVipPrices_100033%3D120.00%26deliveryType%3D%25E7%2589%25A9%25E6%25B5%2581%25E9%2585%258D%25E9%2580%2581%26provider%3D100033%26productId%3DPHYSICAL_100033_35823%26activityId%3D%26totalVipPrice%3D120.00%26allProductPrices_100033%3D120.00%26temp%3D100033%26quantity%3D1%26allPrices_100033%3D120.00%26totalProdcutPrice%3D0.00%26totalPrice%3D120.00%26");
//        StringUtils.startsWithIgnoreCase(str, prefix);
        boolean a =("preUrl=%2FentryCountCentry.action%3FreturnUrl%3DshoppingCart.action%26allVipPrices_100033%3D120.00%26deliveryType%3D%25E7%2589%25A9%25E6%25B5%2581%25E9%2585%258D%25E9%2580%2581%26provider%3D100033%26productId%3DPHYSICAL_100033_35823%26activityId%3D%26totalVipPrice%3D120.00%26allProductPrices_100033%3D120.00%26temp%3D100033%26quantity%3D1%26allPrices_100033%3D120.00%26totalProdcutPrice%3D0.00%26totalPrice%3D120.00%26").contains(".action");
        System.out.println(a);
        c.filter("http://192.168.0.121:8080/ticketIndex.action");
        /*
         * String s =
         * "http://192.168.0.120:8081/logon.action?preUrl=%22%20style%3D%22background:expression(alert(22678))%22%20OA%3D%22 ";
         * int i = s.indexOf("expression"); System.out.println(i);
         * System.out.println(s.substring(0, i)); String a = s.substring(0, i);
         * if(a.endsWith(":")) System.out.println(a.substring(0, a.length() -
         * 1)); System.out.println(s.substring(i+"expression".length()));
         */
    }
}
