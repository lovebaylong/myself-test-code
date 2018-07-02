package cn.holdfun.cn.yaotv.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.lang.StringUtils;

import cn.holdfun.cn.util.UuidUtil;

public class StringTest {

	private static java.text.DecimalFormat df = new java.text.DecimalFormat("00.00");
	private static final Pattern APPID_VALID_PATTERN = Pattern.compile("^[A-Za-z0-9]{18,20}$");
	private static String CANNOT_COPY_LEFTNAME = "report_no,ent_name,ent_no,ent_addr,month,actual_water,plan_water,over_plan_water,over_plan_water_ratio,check_at,report_at";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println((CANNOT_COPY_LEFTNAME).indexOf("report_no"));
		System.out.println((CANNOT_COPY_LEFTNAME).indexOf("main_water_equipment"));
		//System.out.println(Math.abs("olyOrjsiZhC0Q2z9iK5-TvC80vg8".hashCode() % 4));
		/*String useragent = "Lenovo-A399/S100 Linux/3.4.67 Android/4.4.2 Release/06.19.2014 Browser/AppleWebKit537.36 Profile/ Configuration/ Safari/537.36";
		int first = useragent.indexOf(")");
		System.out.println(first);
		String ss = useragent.substring(0, first);
		System.out.println(ss);
		String[] sa = ss.split(";");
		System.out.println(ss.indexOf(";", ss.indexOf(";", 0) + 1));
		System.out.println(ss.substring(ss.indexOf(";", ss.indexOf(";", 0) + 1) + 1).trim());
		String phoneInfo = ss.substring(ss.indexOf(";", ss.indexOf(";", 0) + 1) + 1).trim();
		System.out.println("phoneInfo = " + phoneInfo);
		int firstBlank = phoneInfo.indexOf(" ");
		System.out.println(firstBlank);
		String phoneBrand = phoneInfo.substring(0, firstBlank);
		System.out.println(phoneBrand.trim());
		String phoneModule = phoneInfo.substring(firstBlank);
		System.out.println(phoneModule.trim());*/
		
		/*String referer = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9097d74006e67df3&redirect_uri=https%3A%2F%2Fyaotv.holdfun.cn%2Fportal%2Fapi%2Fmp%2Fauth%2Fsnsapi_userinfo%3FcallBackPage%3Dindex.html%26param%3D%257B%2522cb41faa22e731e9b%2522%253A%25229ChZhkS_a8QDO02RpSqxmyh3rBUUo6qw3dOJI5Z37IsC8Zx1PsUsCyZqJbSPUyiL%2522%252C%2522live_id%2522%253A%2522h5GkyOhaonPNGZR7LKyTT3kXjLPdbheI8EufWsDTZiU%2522%257D&response_type=code&scope=snsapi_userinfo&state=tv_cctv7_life&uin=MjQ1NDYzNTEzNA%3D%3D&key=81e93d7561";
		int i = referer.indexOf("state=");
		System.out.println(i);
		int ii =  referer.indexOf("&", i);
		System.out.println(ii);
		System.out.println(referer.substring(i+"state=".length(), ii));
		System.out.println(referer.contains("open.weixin.qq.com"));*/
		/*String date = DateTimeUtil.getDefinedDayStr(0-0, DateTimeUtil.YYYY_MM_DD);
		System.out.println(date);
		
		Random r = new Random(100);
		JSONArray list = new JSONArray();
		for(int i=0;i<10;i++){
			JSONObject j = new JSONObject();
			j.put("key", i);
			j.put("total", r.nextInt(100));
			list.add(j);
		}
		for(int i = 0;i< list.size();i++){
			JSONObject j = list.getJSONObject(i);
			System.out.println(j.getString("key") + "=" + j.getInt("total"));
		}
		System.out.println();
		sortTotalStatistics(list, "total");
		System.out.println();
		for(int i = 0;i< list.size();i++){
			JSONObject j = list.getJSONObject(i);
			System.out.println(j.getString("key") + "=" + j.getInt("total"));
		}*/
		
		//String currentUrl = "http://yaotv.qq.com/shake_tv/shaketv_new/zip/8s16nsqguo804aqm8emjd/index.html?cb41faa22e731e9b=S-YEY-DmxWn0v065Yiv8g2AbULGD3BgEEhHhOE7ceHnxXU5bh2BDa3cbD8AWb0In&live_id=S_f-yFB_sxNEXAOnOcl-ybZhw5KA9X6JQifHQOvxW6I";
		//System.out.println((analyzeResourceDomain(currentUrl)));
//		System.out.println(Pattern.compile("(av)|(sm)|(诽闻)|-09.info|.csole.com|.mierr.cn|00333.cn|0073d.cn|008567.cn|00zzd.2288.org|01058789.com|0106658.cn|051669676808|0518pw.cn|0519pw.cn|05cctv.cn|06000.cn|07yin.com|08cpw.com|0916ss.3322.org|0920ss.3322.org|0926gx.kmip.net|09city.com|0e1p.9966.org|0os0d.cn|1.1ddxx.com|1.2222d.net|1.33lc.com|1.99game.info|1.acbbs.us|1.aicbbs.info|1.boksx.com|1.clxwe.com|1.dd9b.com|1.doxws.com|1.exksc.com|1.fuwxh.com|1.hxut.net|1.ixwsy.com|1.laoselang.org|1.onlyqq.info|1.psbbs.info|1.pstang.com|1.qinqin.info|1.selaoda.net|1.sexdao.info|1.so8.us|1.tianshi2000.info|1000.dnsxy.cn|10000.25690.cn|10086feix.cn|1014gx3.6600.org|105qngkk.863computer.org.cn|107jj.cn|10wk.cn|11-qq.com|11.ggdmm.com|111.jqtop.com|1111111.aiqiusese.3322.org|1111dnf.kmip.net|115252.com|11748.com|117la.cn|11dgef.cn|11mmm.com|11sss.com|123.sd22ddd.cn|1232355.8866.org|123555662.6600.org|123rruy75.cn|12ff.a125.xunbiz.com|12hxajx.zij.yaq.58ui.90cj.wcs.500caipiao.com.cn|1314r.3322.org|1335122.ggg365.info|1335666.ggg365.info|1336592.ggg365.info|14647.cn|1520ai.kmip.net|15751739424|1777.hk|17gby.com|18dy|18禁|1ct.3322.org|1d.2288.org|1hougong.info|1Pondo|1z.2288.org|2.aicbbs.info|2.boksx.com|2.clxwe.com|2.cxzzd.com|2.doxws.com|2.exksc.com|2.fuwxh.com|2.hupxw.com|2.ixwsy.com|2.maya3.com|2.mm258.org|2.yintuku.com|2009-yahoo.xxcfd.cn|2009.renxian114.cn|200988qq.cn|2009cctv61.cn|2009cctvk.com|2009qqcom.cn|2009sina.eu.kz|2009t.8qbe.cn|2009viv.cn|2010down.cn|21.heziav.info|218318.cn|22.pzwm.info|2233.cc|22ccc.info|22ger.cn|22qsw.com|230it.cn|23331.com|255188.cn|2783ueiiw.cn|28773.cn|297gan.info|2aini.kmip.net|2ijdi.cn|2m2m.kmip.net|2nxx.com|2x3x4x5x6x7x8.count.xj.cn|3.4t5v.com|3.boksx.com|3.clxwe.com|3.cxzzd.com|3.exksc.com|3.fuwxh.com|3.hupxw.com|3.ixwsy.com|308268.cn|308gg.cn|30xj.com|322188.com|33dxdx.com|33xj.net|33xjw.com|3432333.7766.org|3432343.8800.org|34567wyt.cn|35465543.com|360safe.9966.org|360safebox.9966.org|365.tv660.com|365dizhi.com|365ggg.info|36o.8866.org|3838cp.com|3872.qrqr.net|38zu.cn|38zz.info|39sese.com|3ba3h.cn|3d49.cn|3d5805.cn|3d8848.com|3d8886.com|3d90.com|3dcplt.com|3ddmm.com|3eifj.cn|3ggt.kmip.net|3god.net|3rdsddsd.7766.org|3w.08ai.info|3y.2288.org|4.33lc.com|4.aicbbs.info|4.exksc.com|4.hxut.net|4.ixwsy.com|40xj.com|432432ddsd.8866.org|43254543534.9966.org|432ddsds33232.8866.org|435434535.3322.org|43bobo.com|44fang.com|44ghe.cn|454126.8800.org|456.as22456.cn|456.ds11aa.cn|456.ee22456.cn|456.qq22456.cn|456.ww11456.cn|456.ww22456.cn|45678.org|456cao.com|45com.net|47ybnzo.o.o.il.vlfk.os.972wyt.com|5.baigong2000.info|5.ko188.com|50wu.cn|51lacount.cn|51she.info|51vvc.cn|520zhidao.cn|524485072.cn|5252bb.com|52x52.com|52x52.net|53kkk.com|53yyy.com|5435433.8866.org|5435434.2288.org|5435646.2288.org|554313.3322.org|5555se.com|555ty.com|55crw.com|55fang.com|55sss.com|563f235.cn|56665568822.8866.org|56gvdfg444.2288.org|578la.com|57cao.com|58fcw.cn|5918.8866.org|591rb.com|592wg.com|59ri.com|5jyss.com|5mxx.com|5nxx.com|5t434ffss.3322.org|6.acbbs.biz|602hh.cn|61-cctv2.com|61222.com|63ke.com|654.qrqr.net|655996.cn|65gao.com|66289.com|666158.cn|66dxdx.com|66qsw.com|66tt.com|677b.com|67i11.cn|67rrr.com|689cp.cn|68bi.cn|69mmhh.com|6mxx.com|6nnnn.com|6nxx.com|6zg.8800.org|70622.com|711.net|731273265.520815.com|7408.com|76nnn.com|77.haowyt.com|7777788888.com|77bbb.com|77ff.com|77yyj.8866.org|786ts.qqsafe-qqservicesyydswfhuw8ysjftwf.org|788520.com|7crw.com|7y.3322.org|800810down.cn|809678.cn|81yyy.com|857695.3322.org|871cc.cn|87ai.info|88512.cn|88881121223888.rulvbobingainimadeqiu.3322.org|888859.cn|889sese.cn|88qingse.com|89nnn.com|8gggg.com|8lz4.cn|8n20.aabbcc258.cn|8u78.36tt.3322.org|8uuu.36tt.3322.org|8y10.ppccss.cn|8yyxx.com|901s.count.xj.cn|90hou.count.xj.cn|91555.tw.cn|91rbr.com|91rpp.com|92ri.com|92union.com|92xxoo.com|93wx2009qq.cn|94gao.com|9527idc.vicp.net|966543.8866.org|96889.com|97.126wyt.com|97.163wyt.com|97.567wyt.com|97.700wyt.com|97530.cn|977789.com|97bobo.com|97gan.com|97plmm.com|97se97gan97se.info|97sese.com|97sw.aa.am|97sw.cz|97wen.com|97xxoo.com|9821g9.cn|983h2.cn|997wyt.com|9jjxx.com|9kss.com|9nnnn.com|9wxx.com|a.12c.cn|a.77xxoo.info|a.789wyt.com|a.lxwar.com|a.oye333.com|a.qqav.info|a.se2222.com|a.uuzhijia.info|a.xin7778.com|a09230.917.rste.info|a09250.917.rste.info|a1.lxwar.com|a1.pzxz8.com|a2dg2o.cn|a3ny.8866.org|a6633.net|a6ga6.cn|a6tt4.114anhui.com|aa-1.baidushisbnndbuyaoqian.cn|aa.1791xx.com|aa.mimiadd.info|aa1.xorg.pl|aaa.1wbl.com|aaaa6.kmddns.com|aaf.xnum.cn|aag.xnum.cn|aah.xnum.cn|aba.dns0755.net|abcaaaaaabcd.rulvbobingainimadeqiu.3322.org|abcabbaaabcd.rulvbobingainimadeqiu.3322.org|abe.dns0755.net|abe.nna.cc|abf.nna.cc|abg.nna.cc|abh.dns0755.net|abh.nna.cc|abi.dns0755.net|abi.nna.cc|abk.dns0755.net|abn.dns0755.net|abo.dns0755.net|abonlinelive.com|about-bank.cn|about.blank.la|abp.dns0755.net|abt.dns0755.net|acc.dns0755.net|acctv6.s130.288idc.com|acd.dns0755.net|acg.dns0755.net|acr.dns0755.net|act.dns0755.net|active.b2b.hc360.com|acu.dns0755.net|acv.dns0755.net|acz.dns0755.net|ad.googlesyndications.co.cc|ad.keyrvn.co.cc|ad.xxandxy.cn|add.917wan.cn|addvertseense.co.uk|admin1.w51.9410.com|aeefdar34.3322.org|aeo5p.cn|aeu44ry.cn|agjt1h.cn|agn6m.cn|agofa.cn|ai8k.com|ailuo8.com|allfilesstorage.com|amateurmovies.in|ANDEWBLAKE|antivirusplus2010.com|antivirusxp09.com|aq-anquan.com.cn|asc20.com|ase1d.cn|atmus.cn|au-qq.com|AV|av.1234av.com|av.17abo.com|av.17gby.com|av.18abo.com|av.511av.com|av.5jyss.com|av.94aq.cn|av.95ai.com|av.9gaoav.com|av.9kss.com|av.gaoav77.info|av.segg.info|av.ss99ss.info|av130.com|av170.com|avapian.com|avdy8.com|ave1.cn|ave2.cn|ave3.cn|avg.nna.cc|avno1.com|avse.sewytwang.info|avsteel.cn|awebunitra.com|ayahaha.36tt.3322.org|azwyt.com|aƬ|A片|b.17ccc.org|b.97eee.org|b.97wuyue.com|b.avxxx.org|b.goo1.info|b.mimiaa.biz|b.nnbase.com|b.open2009.net|b.oye333.com|b.tuxiao77.com|b.xiao77.biz|b.xiao77luntan.name|b.xin7778.com|b.yinbbs.com|b6tt4.114anhui.com|ba.bahw.cn|baa.xnum.cn|bab.xnum.cn|bad.xnum.cn|badu3.8866.org|bae.xnum.cn|bag.xnum.cn|bahw.cn|bao-2009.cn|bb.297gan.info|bb6oo.cn|bbb.xnum.cn|bbbbtvv.cn|bbc.xnum.cn|bbg2.cn|bbg3.cn|bbg7.cn|bbg8.cn|bbg9.cn|bbs.5hua9.cn|bbs.69111.com|bbs.80xp.com|bbs.889568.com|bbs.guo360.co.cc|bbs.haosecc.com|bbs.hksxs.cn|bbs.keyruns.co.cc|bbs.mp4sky.com|bbssddk.cn|bccccdssse3.7766.org|bd1.kk5c.com|bd2.kk5c.com|bd3.kk5c.com|bdh123.7cv.com|beeblebrox.org|beijingse.net|bexo.2288.org|bha.vedns.com|bigproxy.org|bj788.com|bjcp88.com|blog.niupan.com|blog.qlolo.com|blog.sina-2009vip.cn|bmw7x.cn|bo.1234vv.com|bo.bo73.com|bo33bo.com|bobo.1wwzz.com|bobo.33isese.com|bobo.44fang.com|bobo.55isese.com|bobo.5qqcc.com|bobo.944se.com|bobo.99xjw.com|bobo.wyt09.com|bobobase.com|boboboke.cn|bolapaqir.net|bt.av770.com|bt.ktxp.com|bt9.5qzone.net|buteralksaweda.com|bwla.3322.org|bynrtv.cn|bzefowum.cn|c.92ri.com|c.97eee.org|c.nec8.cn|c.oye333.com|c.xin7778.com|c14.xorg.pl|c1518.drleeuk.com|c1519.drleeuk.com|c333.com|cai99.net|caipiao128.com|caob521.3322.org|cbcpw.cn|cc55f.8866.org|ccc.aiseba.net|ccj1.cn|ccj3.cn|ccj7.cn|cck1.cn|cck3.cn|cctv-c3.cn|cctv-com.cn|cctv-t3.cn|cctv-viplx.cn|cctv.58cq.com|cctv0061.com|cctv2-lyong.cn|cctv2008.bibil0.cn|cctv2ly09.cn|cctv3-0.cn|cctv3-05.cn|cctv3-09.cn|cctv383.com|cctv3e.com|cctv3r.com|cctv3vip.com|cctv3vs.cn|cctv601.com|cctv6ic.cn|cctvaar.cn|cctvdt.cn|cctveg.cn|cctvn2.cn|cde.ss.la|cdfgge.net|ceshikl.7766.org|china60.wauee.com|chinamobilet.cn|chinchoi.cn|chrr2.6600.org|chugui.info|cj.mychina1.cn|ckc01.hdhdv.cn|clearclips.com|cltsi.info|cm2.kmip.net|cn.31bb.info|cn.71bb.info|cnc.592wg.com|cnnncccn.cn|cnzz0.kmip.net|cnzz1.kmip.net|code.979797.com|code.ikaokaoni.cn|code.keyrunn.co.cc|code16.keyrun.com|cofqc.cn|cp01.com|cp02.net|cp28668.cn|cp34568.cn|cp3777.com|cp4858.com|cp52.net|cp6595.cn|cp7870.cn|cp790.cn|cp8258.cn|cp8595.cn|cp88.cc|cp88128.com|cp8868.com|cp8877.com|cp93.cn|cpc.xuebi2000.cn|cpw0688.cn|cpw1828.com|cpw9460.com|cpwoeo.com|cpycwz.com|cpzxyc.com|cq.iqsou.com|cq5566.com|cros.0134.cn|crsa1.cn|csol.xxkk.net|csolss.com|csoltiancity.105.80000web.com.c|ctrip.2288.org|cutalot.cn|cykao.cn|d.1791xx.com|d.17ccc.org|d.caxiao77.com|d.cdwsx.com|d.cxdde.com|d.dingxiang.biz|d.doinw.com|d.eddxx.com|d.fgddx.com|d.gddxw.com|d.hhdwx.com|d.iopqw.com|d.khtgf.cn|d.lnxwy.com|d.mayahot.com|d.ndxwq.com|d.nnbase.com|d.oye333.com|d.xin7778.com|d.xwsfx.com|d.ydopx.com|d10gc.cn|d3.ayxz.com|d3.kkkmfdy.com|d5g5l.cn|d5hsr.cn|d7kj7.cn|dadxx.info|damdang.vn|dasemei.cn|davtraff.com|db9qq.cn|dd1xx.com|dd5xx.com|ddd.66qsw.com|ddd.dasege.com|ddd.ds22aa.cn|dddd3.com|dddl.754245.com|ddi.xorg.pl|ddk1.cn|ddk3.cn|ddl.974671.com|ddl.ur36d.com|ddoszdani.cn|deihc88.h12.idcyh.com|dfetb.cn|dfghs.cn|dfhjfd731.cn|dfsefyt23.open2009.net|dgv9b.cn|dh.41119.cn|dh.97meitu.info|dh.fi5.us|diqu.3322.org|dl.down-china.cn|dl.down-sogou.cn|dl.down-taobao.cn|dl.down-tudou.cn|dl.qvod.com|dlsmg.cn|dn.imsetup.cn|dna.gci-bin.cn|dnf-pp.com|dnfcx.com|dnfgqq.com|dnflipin.cn|dnfqq.net|dnfxi.com|dns.dmy2.com|dnusax.com|do.eDNS.biz|down.114graph.com|down.3333dh.cn|down.langlangdoor.com|down.my227.com|down.qvodlink.com|download.qvodown.com|downloadpoint.cn|drb33en334a.com|dsa4l.cn|dsafdsa3232.8800.org|dsese.com|dsg3aeg.cn|dxnight.com|dxz.974671.com|dydy.com|e.1791xx.com|e.goodhh.com|e.onlybbs.com|e.oye333.com|e.xin7778.com|e.yy4q.com|e11.9966.org|e1ag1j.cn|e6tt4.114anhui.com|edit1china.cn|ee.bww5.cn|ee00oo.cn|ee00r.2288.org|eee.ww22ddd.cn|eelcd5.info|eey88.cn|eg2thg.cn|ejiwu9987.cn|emozzio.emozzio.com|ent.533.com|ertubredxcong.com|ëƬ|E周刊|f.1791xx.com|f.baiyici.cn|f.haodizhi6.info|f8a.ru|fajin.v21.19821122.com|fangspde.cn|farmz.cn|fartunaall.ru|fc03.com|fc151.cn|fc178.com|fc3d558.com|fc56588.cn|fc5688.net|fc5898.cn|fc6188.cn|fc689.cn|fc998.com|fdccdf333.7766.org|fdsa3324324.8866.org|fdsfdscvdfde4.6600.org|fdsgdfgh.2288.org|fdvdv.cn|fefewcdz32.3322.org|feixin.979797.net|feixin591.cn|feixini2009.cn|feixinte.com|feixiy.cn|feixn2009g.cn|fetion-cctv.cn|fetion006.com.cn|fetionc.com|fetioy.com.cn|ff1112.2288.org|fg4yjv.cn|fiio.8800.org|file.plusbag.net|file.sayclub.co.kr|flcp315.com|flcp688.com|flcp8848.com|flxh.2288.org|foqffo.cn|frantsuzik.com|freedom3.cn|fsgsddk566585fdjhdu559594hfghjdgkdcom|ft916.8800.org|ft917.3322.org|fu456.com|fuck|futjap.net|fvbvbrt.2288.org|fx2009jj.cn|fxok.cn|fyowf.cn|g1476.cn|g4k4h.cn|g6tt4.114anhui.com|g9e9g.cn|gae1hasdf.cn|game.guo360.co.cc|gbv.nna.c|gda.nna.cc|gday.3322.org|geocities.com|gf86.cn|gfr24.com|gg.woyinwos.cn|gghy7785.xw2.cdn9.info|ghostusers.info|gidromash.cn|gjrwdl.cn|gjy.nna.cc|gkl.nna.cc|good-week.cn|google-analystiks.com|google.googlee10.cn|google.maniyakat.cn|gospel-force.com|gotothefile.com|gq8g.cn|granata282.cn|gvb.nna.cc|gyl.nna.cc|G点|h.wowoer.com|h8ae8.cn|ha10020.ww.stx8.com|haa.ss.la|haea.3322.org|haibo.16f.cn|hao.147xxoo.com|hao.29smm.com|hao.600xxoo.com|hao.92xxoo.com|hao.97xxoo.com|hao123.lian88.com|haohaobo.org|haosecc.com|happy-fxs.com|happyfarms.cn|happyqqfarm.cn|heqiuyan.cn|hh.qvod-50.com|hh8.ss.la|hhj1.cn|hhj2.cn|hhj3.cn|hhj7.cn|hhqvod.cn|hi.52kuaibo.info|hichina|historycontext.cn|hnbc2.cn|hookbaes.cn|hot44.kmip.net|hou2.6600.org|houtai.185555.com|hsdfhh32131231.3322.org|htiwg.cn|huang.dizhi.info|huang.huanghuang.info|huangpian.biz|huangsidianying.cn|i.egooad.cn|i18o.cn|i31.net.ru|i3219.cn|iamchinese.info|iggoole.cn|iioossddxx.3322.org|iis.mo.cn|ij55ar.cn|ILLUSION|imagehut4.cn|img.downbt.com|imso3rry.count.xj.cn|iowhi.cn|iq-qq.com|ironpunch.com|itorkadflione.com|j7723.cn|jamesfans.cn|jdccn.com|jeiuei82872.cn|jhgyuty.cn|jiangdongiji|jiayifan.cn|jievj.cn|jindome.cn|jiqingmm.com.cn|jiqingroom.cn|jjjjyayayaya.3322.org|jk.258wyt.com|jkk.tw|job.cust.edu.cn|jockeybbs.cn|jp.93bb.info|jqdy.6600.org|js.mumayi.net|jsani.cn|julionejurmon.com|jw961.cn|jx.changyou.com|jxox.8800.org|jz926.3322.org|k.6xx8.com|k.kekese.com|k17mm.3322.org|k927.9559cck.cn|kaba.979797.net|kaixin-200.com|kaj11mv122i.com|kan.sewytwang.info|kao17.cn|kazirnayatema.cn|kcot.3322.org|kied.9966.org|kievsk.com|kj33ijfek.cn|kj8l.kmip.net|kk.henhao321.cn|kkaf.dns0755.net|kkaj.dns0755.net|kkak.dns0755.net|kkan.dns0755.net|kkhaomm.cn|kmm.ss.la|knownsec.8800.org|kof.flashwing.net|kojd.9966.org|kokkosik.com|kolinhopewaqs.com|komojoke.cn|kpaxikey.cn|kpizuyuw.cn|ku4000.cn|kumogroup.cn|kvf.dns0755.net|kybbt.cn|K粉|langke.bad.mn|langmm.us.to|leoe.3322.org|li28.vicp.net|lian.10000ip.cn|liao.577renti.cn|liao.mm972.com|linbiao.9966.org|lingqi-taobao.com|link666.info|litianyi.down.lv-n.cn|liuhecai.cc|liushizhounian.qqxuexiao.com|liuyang.h19.y733.cn|lm.qsjiuba.com|lo1j.8800.org|loveyou.wwooaini88|lucheng.us|m-qq.com|m.99081.com|m6mm.info|mailvip1-6-3.cn|maopian.cc|maopian.in|masterwanker.com|maya.com|mbr3.cn|MC军团|mdsfd.cn|mggyu.info|mhzxz.com|mimaer.3322.org|minfo.com|minghuinews|mir2.sdo.mx|mir2.sdoma.cn|mirh2.com|mirs.sh.cn|mm.568wyt.com|mm.700wyt.com|mm.97gan2.info|mm.aa88567.cn|mm.niuniujidid.com|mm.qeshow.com|mm22.kmip.net|mmhouse.info|mmhouse.me|mmpp258.com|mmpp321.com|mmpp654.com|mmv88.info|mo.163wyt.com|mo.997wyt.com|mo171.cn|moumom.cn|mstrh4.cn|multimedia|musicmobi.com.cn|my-correios.com|my111.info|myar.dns0755.net|mychina1.cn|mymir.sdo1.cn|mymir.sdq.name|myndomain.info|myskynet.info|mysq1.cn|mytotalscanner17.com|mywebsearch.com|nba.charw.com|nbbfrdfsfe.8800.org|nbdmm.3322.org|neenos.cn|nenastiya.cn|nenoos.com|ngfres.com|ngytrd.com|nhy7ubgv.114anhui.com|ni0.ss.la|nicidc.com|nifeng03.3322.org|niggals.com|niuniu3.cn|nju7yd.3322.org|nm11df.cn|nmgzlt.cn|nnd|nnyp.net.cn|nonoes.cn|nonoss.cn|novatoriz.cn|ns123.com.cn|ntrjj.cn|nusatorkaleprovis.com|o.m6co.net|oa.dfac.com|odn45qa555l.com|officea.flnet.org|ok.400wyt.com|ok.paofangwuyuetian.info|ok.qvodiii.com|okiloi123.3322.org|onelun.cn|online-media-archive.net|online.guo360.co.cc|org-edu.ru|oxmu.7766.org|p1.hhbbs.info|p1.images22.51img1.com|p2.images22.51img1.com|p22.likehh.com|p2p.cc440.com|p2p.cs770.com|p3.images22.51img1.com|p5.highsms.com|p7ld.cn|paganinihttpdown.go.3322.org|paobar.com|parkelec.co.kr|partner.name8.cn|pcikpt.vicp.net|PE-6拦截器|peacehall|pian|pic.360gan.com|pic1.images-yite.cn|ping600.info|PK黑社会|playboy|poiujhnbg.com|pop3.123fga.cn|popkarrt.cn|pornhub.com|pp10.info|pp99bb.cn|ppvod2010.cn|presentes.com.cn|pt930.9966.org|pzyou.us|q2009q.com|q9sx.cn|qcd127.com|qer67.com|qingse99.3322.org|qingss.3322.org|qk102.com|qm1.bbshh.info|qm8.bbshh.com|qnq61.cn|qq|qq-2009-vip.cn|qq-2009tx.com|qq-2009vp.cn|qq-a2009.cn|qq-qq10n.cn|qq-tt09.cn|qq-vip55.cn|qq-vj6.cn|qq-x666.cn|qq.10zn.cn|qq.265lian.com|qq.qqq58.cn|qq.s132.288idc.com|qq1.bbshh.info|qq10000-vip.cn|qq10000ky.cn|qq10txx.com|qq12345.kmip.net|qq199vip.cn|qq200909.com.cn|qq2009ds3.w223.bizcn.com|qq2009et.com|qq5658.com|qq6523.cn|qq86666.cn|qq8899.002.szflw.com|qq8d8.cn|qq949023.free2.iisiisiis.com|qqad2009.cn|qqajkluioy.3322.org|qqaon.com|qqaou.com|qqaov.com|qqazxc.cn|qqbrtr.cn|qqcej.cn|qqcs2009.cn|qqfarmb.cn|qqfarmd.cn|qqfarme.cn|qqfarmg.cn|qqfarmr.cn|qqfarmw.cn|qqfarmx.cn|qqfarmy.cn|qqfrw.cn|qqh2009.com|qqhaomm.cn|qqhx.uugua.cn|qqju.org|qql10.cn|qqm.name|qqnae.com|qqo68.cn|qqokq.com|qqootv.cn|qqqq2009.com|qqrgb.cn|qqsevice.com.cn|qqsp09.cn|qqtouxiang.keaiq.com|qqtq.open2009.net|qqtrrt.com.cn|qqtxun.cn|qqtxvip9.com|qquc.0130.6464.cn|qqupm.cn|qqv0088.com.cn|qqvip.org|qqvip009.com|qqvip2.cn|qqvip8.com|qqvipo.usa58.net|qqvong.cn|qqwet.com|qqxs6.cn|qqxuy.cn|qqzock.cn|qqzxcjklhgy.3322.org|qqzzxxaass.3322.org|qq幸运用户抽奖|qs.654wyt.com|quanj2ia.count.xj.cn|quu.wqiu.x.eki.ssk.os.972wyt.com|qvod-8.cn|qvod088.com|qw2009k.cn|qwedasertafoas.com|qwr1.cn|qwr7.cn|qxqx.7766.org|r1.xwsfx.com|r2.xwsfx.com|r22.9966.org|r25281.nb.host192-168-1-2.com|r3.xwsfx.com|r4ikm.cn|ravelotti.cn|rewdsds333.8866.org|rss-lenta-news.ru|s.163686.cn|s15.b387yh3uyh.3322.org|s23.b387yh3uyh.3322.org|s33.idc.xpli.cn|sao30.com|sc7w.cn|scantoolsite.com|scanw.3322.org|scanw.8800.org|scanw.8866.org|scw666.com|scw678.cn|sdo.ms|sdse12345.cn|se.0512pw.com.cn|se.1234cao.com|se.1234xxx.com|se.123haobaidu.com|se.163wyt.com|se.17gby.com|se.1qqxx.com|se.1txx.com|se.20bobo.com|se.22isese.com|se.258wyt.com|se.29smm.com|se.2qqxx.com|se.32rrr.com|se.32xxoo.com|se.33isese.com|se.33xxoo.com|se.400wyt.com|se.456pao.com|se.456wyt.com|se.500xxoo.com|se.552se.com|se.55crw.com|se.55isese.com|se.567nba.com|se.567wyt.com|se.5jyss.com|se.5nxx.com|se.5qqcc.com|se.6322.info|se.654wyt.com|se.66qsw.com|se.678wyt.com|se.6bbss.com|se.6nxx.com|se.789wyt.com|se.7bxx.com|se.7crw.com|se.800wyt.com|se.816xx.com|se.900wyt.com|se.91rbr.com|se.92xxoo.com|se.940wyt.com|se.94gao.com|se.97ai.com|se.97sewyt.info|se.97wen.name|se.987wyt.com|se.997wyt.com|se.9kss.com|se.9nnnn.com|se.a6633.net|se.aafang.com|se.ai8k.com|se.av-av.info|se.beijingxx8.com|se.c66a.cn|se.chengrenwang.biz|se.qingwuyuetian.info|se.qswyt.in|se.sao94.com|se.se-sese.net|se.semeimei.org|se.sexdy7.com|se.sqwyt.im|se.sqwyt.usv|se.tswyue.cn|se.woyaoxingjiaowang.net.cn|se.wyt.im|se11.info|se11se.net|se13se.com|se13se.net|se14se.net|se15se.com|se52se.com|secure-cn.imrworldwide.com|securitytestinternetguide.com|sese.rentihome.com|seset.com|setao.info|sex|sex52sex.com|sexba.3322.org|sexba.budu.cc|sexba.cz|sexba.nu.mu|sexdy7.com|sg.iyoyo.com.cn|sg165.9966.org|shadu.eastad8.cn|shangav.com|shangshan.w990.vhostgo.com|sharetechnology.cn|shipin39.cn|shit|shop.taobao359009580.com|shuihu.miduo.com|siwamm.net.cn|sk01.9966.org|sleepatnight.cn|smwy.siwang2010.com|SM用品|so11.cn|socks5service.cn|sockslab.net|souhu.com|space2009city.cn|spafeixin.cn|sportsbay.cn|sporttoday.cn|sq1l.cn|sql.55gg.com|ss.likekong.com|ss52ss.com|ssdao.com|sshsex.info|ssss.66qsw.com|stallvars-2.cn|staticdne.9966.org|staticdnf.9966.org|statviewb.9966.org|sysconsolutions.co.za|t3hrf.cn|taobao359009580.com|tb0102-vip.cn|tengxun-2009qq.cn|tengxun001.3.53dns.com|test1.9966.org|test2.9966.org|thankyou1.count.xj.cn|The3FeelOnline|theson.com.cn|tiangleboy|tianjincn.cn|tingtingjidi.com|tinyurl.com|tjt12wg223s.com|TMD|TND|TNND|tonglve.cn|top.rxcrw.com|top.veeww.com|top568.info|tougao.jxcn.cn|trenmeirland.ru|tse99.info|tt.kkmm8.com|tube1820.com|tv-net.cn|tv-viple.cn|tv-viplx.cn|tv0061.com|tv2-tv3.cn|tv2ly09.cn|tv383.com|tv601.com|tvaar.cn|tvb5.6600.org|tvdt.cn|tx-qq.com|txqq10000-vip.cn|txqq2009-vip.cn|txqq2009vipx.cn|txt.efgvd.com|txt.iuyxc.com|txt.loawd.com|u.1133.cc|u.gpask.cn|u88.cn|uade.3322.org|uday.2288.org|uhajokvfalesko.com|uin1.cn|uin2.cn|uin3.cn|uin4.cn|uin5.cn|uiterbunagoretas.com|ukwirex.info|ulibertagolionas.com|UltaSuf|union.996116.cn|unixbox|up.s0so.net|urj0.cn|usa-yabao.com|ustibet|uu12.2288.org|uyjjk8.x2w.cdn9.info|vabrm.cn|vae.nna.cc|vccd.cn|vdsvcsa.3322.org|vdswrt.com|veuo.cn|vip.77169.com|vip.qq.wz12.com|vip8fetion.com|vkt55wg666r.com|vod.seset.com|vpn318.cn|vty8p.cn|vvk1.cn|vvk2.cn|vvk3.cn|vvk5.cn|vvk7.cn|vvk9.cn|vytf.com.cn|w.guo360.co.cc|w1.6yh4.com|w1.849jfg.com|w1.dddddd32.com|w10255.s4.needidc.net|w2.6yh4.com|w2.dddddd32.com|w2dge.cn|w3.6yh4.com|w3.849jfg.com|w3.dddddd32.com|w8.6yh4.com|w8.849jfg.com|w8.dddddd32.com|w9.6yh4.com|w9.849jfg.com|wan886.com|web99222840.web159.dikavan.net|wer1234.cn|wervaferganiota.com|wg1185.cn|wg879.cn|wi66db.cn|wm.1kfie.cn|wm.245a34.cn|wm.2e7860.cn|wm.5fqwf.cn|wm.7udij.cn|wm.888b123.cn|wm.8iopi.cn|wm.990248.cn|wm.99c342.cn|wm.9wfij.cn|wm.ds4ijf.cn|wm.ds5iop.cn|wm.egjee.cn|wm.yxnjs.com|wm7.semonk.com|wm8798.3322.org|wmdag3fd.3322.org|wo8.ss.la|woaini520.web003.boothost.com|wowshe1l.com|ws-qq.com|ws071121.3322.org|wstaiji|wut99.cn|wuxifdc.cn|wvg6.cn|wvg7.cn|ww.wyt365.cn|ww3.niupan.com|wws.mobiec.net|www-114la.com|www-qq.net|www.0073d.cn|www.008567.cn|www.00ww.com|www.01058789.com|www.0106658.cn|www.01kao.com|www.02wyt.com|www.03wyt.com|www.04wyt.com|www.0592001.com|www.05cctv.cn|www.05wyt.com|www.07wyt.com|www.07yin.com|www.08cpw.com|www.08ri.com|www.0914114.cn|www.09979.com|www.09qq8.cn|www.10000-tenxu.cn|www.1000mov.com|www.1000ys.com|www.10086feix.cn|www.100ri.com|www.108ri.com|www.10qq.info|www.10wk.cn|www.11-qq.com|www.110139.com|www.111333.com.cn|www.111ball.com|www.111cao.com|www.1149.cn|www.114la.com|www.115252.com|www.11748.com|www.1188.com|www.11990.com|www.11fv.com|www.11kfc.com|www.11mmm.com|www.11se.com|www.11sss.com|www.11sss.name|www.11wyt.com|www.11xp.cc|www.121g.cn|www.1234vv.com|www.123xiaoshuo.cn|www.126disk.com|www.126rtys.com|www.126wyt.com|www.12902.com|www.135968.cn|www.135f.com|www.137377.com|www.151bobo.com|www.1551777.com|www.15cr.info|www.15xb.com|www.16205.com|www.163mxd.com|www.163wyt.com|www.16668.com|www.1666x.com|www.167bt.com|www.16fff.com|www.171k.cn|www.173sese.com|www.173sese.net|www.177155.net|www.177dvd.cn|www.178sese.com|www.17fc.com|www.189qq.cn|www.18vipmm.cn|www.199se.com|www.1gggg.com|www.1hougong.info|www.1mm2nn.cn|www.1setu.cn|www.1txx.com|www.1uuuu.com|www.1wanjia.cn|www.200988qq.cn|www.2009cctvk.com|www.2009yct.cn|www.2010down.cn|www.20xj.com|www.2121a.com|www.2144w.com|www.218318.cn|www.21sexporn.com|www.2222ye.com|www.222tu.com|www.2233.cc|www.2233234.com|www.223359.com|www.2234.net|www.22aaa.com|www.22ccc.biz|www.22ccc.com|www.22fv.com|www.22isese.com|www.22qsw.com|www.22wyt.com|www.230it.cn|www.23331.com|www.23406.com|www.2525245.cn|www.2548.cn|www.255188.cn|www.25aa.info|www.25isese.com|www.25sys25.cn|www.2637.cn|www.26dh.cn|www.28995.com|www.297gan.info|www.29smm.com|www.29wz.com|www.2jjxx.com|www.2mxx.com|www.2nxx.com|www.2txx.com|www.2wyt.com|www.303555.com|www.308268.cn|www.30xj.com|www.3110.cn|www.318zq.cn|www.321hack.com|www.322188.com|www.32aise.com|www.32gao.com|www.32rrr.com|www.32sese.com|www.32wyt.com|www.332258.cn|www.332dh.com|www.3333ss.com|www.3333xb.com|www.333cao.com|www.33533.com|www.3363.cc|www.3374.com|www.33bbb.com|www.33isese.com|www.33kfc.com|www.33mao.com|www.33ri.com|www.33xj.com|www.33xj.net|www.344999.com|www.34567wyt.cn|www.345xb.com|www.34gao.com|www.34wyt.com|www.3527.com|www.365j.com|www.365kong.com|www.365wz.net|www.365x.us|www.365xx.info|www.3838cp.com|www.38995.com|www.38haose.com|www.38hoo.com|www.38ssss.com|www.38ttt.com|www.38wa.com|www.38zhu.com|www.38zz.info|www.3929.cn|www.39sese.com|www.39xingfu.cn|www.3d49.cn|www.3d5805.cn|www.3d8848.com|www.3d8886.com|www.3d90.com|www.3dcplt.com|www.3ddmm.com|www.3ddxx.com|www.3i7.cn|www.3mxx.com|www.3nxx.com|www.3se3.com|www.4000qq.com|www.40ri.com|www.40xj.com|www.41nnn.com|www.424288.cn|www.43166.com|www.43bobo.com|www.44aaaa.com|www.44fang.com|www.44xj.net|www.44xp.com|www.456cao.com|www.456yin.com|www.45nnn.com|www.4749.com|www.48bbb.com|www.4947.com|www.4gxx.com|www.4mxx.com|www.4nxx.com|www.4vk.cn|www.4wyt.com|www.505se.com|www.50wu.cn|www.50xj.com|www.511u.com|www.51files.com|www.51mmtu.com|www.51mxd.com|www.51she.info|www.51vvc.cn|www.51xj.com|www.51xj.org|www.51xyxi.mobi|www.520hack.com|www.520poco.cn|www.520zhidao.cn|www.521dx.com|www.522dx.com|www.52416.com|www.524485072.cn|www.524pin.com|www.5252bb.com|www.5252se.com|www.52dp.com|www.52eshu.com|www.52ssss.net|www.52x52.com|www.53449.com|www.53gan.com|www.53kf.com|www.53kkk.com|www.53nnn.com|www.53yyy.com|www.55229.com|www.55266.com|www.552wg.cn|www.5555se.com|www.555ri.com|www.555ty.com|www.55crw.com|www.55fang.com|www.55isese.com|www.55sq.com|www.55sss.com|www.55xj.net|www.567722.com|www.567wyt.com|www.56pao.com|www.56zz.com|www.57cao.com|www.57xxoo.com|www.588699.com|www.588x.com|www.58fcw.cn|www.58xxoo.com|www.591rb.com|www.592lv.cn|www.59ri.com|www.5d6g.cn|www.5dx.info|www.5ibcc.com|www.5mxx.com|www.5nxx.com|www.5qqcc.com|www.5qzone.net|www.5yin5se.cn|www.5yyxx.com|www.600999.com|www.60xj.com|www.611cn.com|www.61222.com|www.61229.com|www.61kkk.com|www.61nnn.com|www.61yyy.com|www.62yyy.com|www.63ke.com|www.651x.cn|www.65465.cn|www.65488.com|www.654v.com|www.655996.cn|www.65gao.com|www.66289.com|www.666158.cn|www.6666xb.com|www.666cao.com|www.66aaaa.com|www.66kan.com|www.66qsw.com|www.66ri.com|www.66xb.com|www.6700.cn|www.677b.com|www.6781.com|www.67iii.com|www.67rrr.com|www.67sese.net|www.688365.com|www.68bi.cn|www.68la.com|www.69mmhh.com|www.69uu.com|www.6d6d.net|www.6ffff.com|www.6gggg.com|www.6k-taobao.com|www.6mxx.com|www.6nnnn.com|www.6nxx.com|www.6u666.com|www.6wyt.com|www.6xx8.com|www.6yt.info|www.7000se.com|www.70622.com|www.70881.com|www.71566.com|www.7185.com|www.7241.cn|www.7408.com|www.74tube.com|www.767666.com|www.76nnn.com|www.77112.com|www.7777788888.com|www.7777ss.com|www.777mi.com|www.77816.com|www.778xx.com|www.77bbb.com|www.77dydy.com|www.77ff.com|www.77wyt.com|www.78777.cn|www.788007.com|www.788520.com|www.788799.com|www.789bb.com|www.789wyt.com|www.78ai.com|www.78gan.com|www.78hk.net|www.79kk.cn|www.7bxx.com|www.7crw.com|www.7ddxx.com|www.7f7f.com|www.7kkt.com|www.7mxx.com|www.7wyt.com|www.800810down.cn|www.800wyt.com|www.802888.com|www.803456.com|www.809678.cn|www.816xx.com|www.81kkk.com|www.81yyy.com|www.82190.cn|www.82nnn.com|www.82yyy.com|www.833v.com|www.8687.cn|www.86zw.com|www.87209.com|www.87265.com|www.8788se.cn|www.87919.com|www.87ai.info|www.880333.com|www.88374.com|www.88454.com|www.88512.cn|www.88685.com|www.888859.cn|www.8888888888888888888888888888888888888888888888|www.8888888888888888888888888888888888888888888888|www.8888se.com|www.8888ye.com|www.889sese.cn|www.88xj.net|www.89178.com|www.89233.com|www.89744.com|www.89nnn.com|www.8gggg.com|www.8jie.info|www.8lz4.cn|www.8mdl.com.cn|www.8mxx.com|www.8nnnn.com|www.8y19.com|www.8yyxx.com|www.900wyt.com|www.90ca.cn|www.90ri.com|www.91555.tw.cn|www.91abo.com|www.91qianming.cn|www.91rpp.com|www.91youa.com|www.921xx.com|www.92dh.net|www.92ri.com|www.92union.com|www.92xxoo.com|www.930930.com|www.9348.cn|www.93wx2009qq.cn|www.9486.cn|www.94950.cn|www.94bbb.com|www.9522.cn|www.95selang.com|www.96selang.com|www.97530.cn|www.977789.com|www.979c.com|www.97ai.com|www.97aiso.com|www.97bobo.com|www.97cao97gan.cn|www.97gan.com|www.97gan.info|www.97gao.com|www.97isese.com|www.97ixxoo.com|www.97kkk.com|www.97mama.com|www.97mo.com|www.97se.com|www.97sese.com|www.97sese.name|www.97ssw.com|www.97wen.com|www.97xj.com|www.97xxoo.com|www.97zxy.com|www.9800ok.cn|www.980wyt.com|www.99099.info|www.99123.com|www.996333.com|www.997wyt.com|www.9991234.com|www.999155.com|www.9999q.com|www.999mimi.net|www.999ri.com|www.99abo.com|www.99dydy.com|www.99fv.com|www.99mmm.com|www.99selang.com|www.99wyt.com|www.99xj.net|www.99xjw.com|www.9cvv.com|www.9gggg.com|www.9jjxx.com|www.9jwp.cn|www.9kss.com|www.9mxx.com|www.9nnnn.com|www.9nxx.com|www.9ssdd.com|www.9wxx.com|www.a6633.net|www.aapao.com|www.abada.cn|www.ablank.cn|www.addwww.cn|www.adult168.com|www.ailuo8.com|www.aiongames.com|www.aiqianming.cn|www.aise.cc|www.aisetu.cn|www.aisex.com|www.aiyoo.net|www.am|www.amateurmovies.in|www.ankty.com|www.any2000.com|www.anyiss.com|www.aoao1.com|www.aoao2.com|www.aq-anquan.com.cn|www.aqay.cn|www.au-qq.com|www.av130.com|www.av170.com|www.avapian.com|www.avonshop.cn|www.avttt.com|www.avurl.com|www.axmeinv.cn|www.azf2.cn|www.azwyt.com|www.baidu345.com|www.bao-2009.cn|www.bb2000.net|www.bb5yt.com|www.bbbbtvv.cn|www.bbbqqq.com|www.beauty3.cn|www.beijingxx.com|www.bencibo.cn|www.bets365.com.cn|www.bgfw6.cn|www.bibilu.cn|www.bj788.com|www.bjcp88.com|www.bjlanjingling.com|www.bo33bo.com|www.bobo.3ggss.com|www.bobobase.com|www.boboboke.cn|www.boxdh.com|www.bqssf.com|www.brans.pl|www.cai99.net|www.caipiao128.com|www.caoliu.name|www.caowa.com|www.cbcpw.cn|www.cc|www.cccom/|www.cctv.vipa5.cn|www.cctv3-05.cn|www.cctv3-09.cn|www.chadnf.cn|www.chengrenshipin.com|www.chinaboji.net|www.chinamobilet.cn|www.chugui.info|www.cltsi.info|www.cn762.com|www.cnceres.com|www.coopen.cn|www.cp01.com|www.cp02.net|www.cp28668.cn|www.cp34568.cn|www.cp3777.com|www.cp4858.com|www.cp52.net|www.cp6009.cn|www.cp6595.cn|www.cp756.cn|www.cp7870.cn|www.cp790.cn|www.cp8258.cn|www.cp8595.cn|www.cp88.cc|www.cp88128.com|www.cp8868.com|www.cp8877.com|www.cp93.cn|www.cpw0688.cn|www.cpw1828.com|www.cpwoeo.com|www.cpycwz.com|www.cpzxyc.com|www.cq5566.com|www.csolss.com|www.cutalot.cn|www.cuteqq.cn|www.cxtv1.cn|www.cyboma.com|www.dadxx.info|www.dasemei.cn|www.dd1xx.com|www.dd2xx.com|www.dd3xx.com|www.dd4000.cn|www.dd444.com|www.dd5xx.com|www.dd88567.cn|www.dddd3.com|www.ddk1.cn|www.ddk3.cn|www.ddosgj.cn|www.ddoszdani.cn|www.deoda.cn|www.dfetb.cn|www.dh234.com|www.di8se.com|www.dianwo.com|www.dlsmg.cn|www.dnfcx.com|www.dnfgqq.com|www.dnfjp.com.cn|www.dnflipin.cn|www.dnfqq.net|www.dnfxi.com|www.dnusax.com|www.do.eDNS.biz|www.downtool.com|www.dsese.com|www.duczz.com|www.dukeba.com|www.dxbbs3.info|www.dxbbs4.com|www.dxcpm.com|www.dxdx1.com|www.dxdx8.com|www.dxlove.net|www.dxnight.com|www.dxslife.cn|www.dxxdxx.com|www.eduzhai.net|www.ewspw.cn|www.f17f.cn|www.fangspde.cn|www.fangyouw.com|www.fc151.cn|www.fc3d558.com|www.fc56588.cn|www.fc5688.net|www.fc5898.cn|www.fc6188.cn|www.fc689.cn|www.fdvdv.cn|www.fei4.cn|www.feirenmy.com|www.feixin591.cn|www.feixini2009.cn|www.feixinte.com|www.feixiy.cn|www.fenxin-cctv.com|www.fetion-cctv.cn|www.fetionc.com|www.fetioy.com.cn|www.fireol.com|www.flcp315.com|www.flcp688.com|www.flcp8848.com|www.foqffo.cn|www.freedh.com|www.fu456.com|www.funso8.com|www.fxok.cn|www.fzluc.com|www.g1476.cn|www.g51s.cn|www.gaoav.com|www.ggdaohang.com|www.ghost2.cn|www.ghostxp3.com|www.gjrwdl.cn|www.go2000.cc|www.go2000.cn|www.goodhao188.com|www.grodno-online.com|www.guo360.co.cc|www.gzhhy.com|www.haidaowan.net|www.haoda123.com|www.haodifang.net|www.haodizhi.com|www.haoha123.com|www.haohaobo.org|www.haomir.com|www.haorenti.com.cn|www.haosecc.com|www.heqiuyan.cn|www.hh97.com|www.hhmm88.com|www.himi.co.kr|www.hjsdjcn.com|www.hk0707.com|www.hk0909.com|www.how21.com|www.htm5.co.cc|www.htm6.co.cc|www.htqq4.cn|www.huangpian.biz|www.huangsidianying.cn|www.hx95.com|www.i8844.cn|www.i8gaming.com|www.idownsoft.com|www.ie7.com.cn|www.ihehua.com|www.iiiland.info|www.iiiland.us|www.im900.com|www.ipqq.org|www.iq-qq.com|www.is999.net|www.isii.cn|www.ixxoo.info|www.jdccn.com|www.jddm.cn|www.ji11.cn|www.jianpu.cn|www.jiegt.cn|www.jinhezx.net|www.jiqingmm.com.cn|www.jiqingroom.cn|www.jiuqubobo.com.cn|www.jiuquga.cn|www.jiuqugan.info|www.jiuqugan.net|www.jiuqumama.com|www.jjjppp.com|www.jjol.cn|www.jjttt.com|www.jqdby.com|www.jsing.net|www.k333.cn|www.k73kd83.cn|www.k986.com|www.kaicn.com|www.keyrvn.co.cc|www.khlyb.cn|www.killsoft.cn|www.kiraabc.com|www.kissdh.com|www.kissdh.info|www.kk4000.cn|www.kkksss.com|www.kkmm8.com|www.kkwyx.com|www.klyx8.com|www.kolinhopewaqs.com|www.kpsky.cn|www.ku256.com|www.ku4000.cn|www.kuku123.com|www.kwxf.com|www.kz123.cn|www.kzxf.net|www.landmarklondon.mobi|www.langke.xxl.st|www.laoquanwater.com|www.leledh.com|www.liangxing163.cn|www.liaojiewo.com|www.linkbucks.com|www.lnrc.com.cn|www.luanxing.com|www.lucheng.us|www.lusongsong.com|www.lywlqy.com|www.lzstu.cn|www.m-qq.com|www.m6mm.info|www.mamase.com|www.mandodns.com|www.maopian.cc|www.maopian.in|www.masterwanker.com|www.mayisf.com|www.mb171.cn|www.meihuaweijing.cn|www.meimeidy.com|www.meizhou5.com|www.mengqinie.com.cn|www.mggyu.info|www.mhzxz.com|www.mimiyan.net|www.mir2.sdoma.cn|www.mir2sdo.biz|www.mirh2.com|www.mirs.sh.cn|www.mirwow.com|www.mm5m.com|www.mm5yt.com|www.mm92se.com|www.mmaiai.com|www.mmc2009.com|www.mmliao.com|www.mmpp123.com|www.mmpp321.com|www.mmpp456.com|www.mmpp654.com|www.mmsetu.com|www.mmv88.info|www.mmzu.net|www.mn866.com|www.mo171.cn|www.moumom.cn|www.movedy.com|www.mrbsd.org|www.mscd.cc|www.musicgu.com|www.musicmoa.net|www.mxfwb.com|www.my111.inf|www.my180.com|www.mytvbt.com|www.n0023.cn|www.neenos.cn|www.nenf.cn|www.net9981.cn|www.newforum.co.kr|www.newsreach.cn|www.niggals.com|www.nisema.com|www.niuniu3.cn|www.niuniujidi.net|www.nnyp.net.cn|www.nonoes.cn|www.nonoss.cn|www.nosenn.cn|www.noseon.cn|www.nosesn.cn|www.nosnnn.cn|www.nosnsn.cn|www.nosoen.cn|www.nososn.cn|www.nossnn.cn|www.nosssn.cn|www.nvsex8.cn|www.okdianying.com|www.oneku.com|www.onsese.com|www.oyesgo.com|www.pao44.com|www.pcmfw.com|www.pcpcw.cn|www.pcunion.cn|www.pereca2009.com|www.petdoso.com|www.pin6.com|www.ping600.info|www.popkarrt.cn|www.popkart88.cn|www.pornhost.com|www.pornhub.com|www.pp10.info|www.pp365.com|www.presentes.com.cn|www.pumch.ac.cn|www.pzyou.us|www.q160.com|www.q2009q.com|www.q7788.com|www.q9sx.cn|www.qhuc.com|www.qiandu.info|www.qise.info|www.qishi.com|www.qk102.com|www.qnq61.cn|www.qq-2009tx.com|www.qq-2009vp.cn|www.qq-a2009.cn|www.qq-anqun.com.cn|www.qq-tt09.cn|www.qq-vip55.cn|www.qq-vj9.cn|www.qq-x123.cn|www.qq-x666.cn|www.qq.com.aaadmin5.com|www.qq0y.cn|www.qq10txx.com|www.qq140.com|www.qq163vip.cn|www.qq200909.com.cn|www.qq2009et.com|www.qq2009hv.com.cn|www.qq2010qq.cn|www.qq5.com|www.qq54k.cn|www.qq5658.com|www.qq627.cn|www.qq86666.cn|www.qq8d8.cn|www.qqad2009.cn|www.qqaon.com|www.qqaou.com|www.qqaov.com|www.qqazxc.cn|www.qqcej.cn|www.qqcs2009.cn|www.qqfarmw.cn|www.qqfrw.cn|www.qqh2009.com|www.qqkhth.cn|www.qqkpz.com|www.qqluck2009.cn|www.qqm.name|www.qqo68.cn|www.qqokq.com|www.qqootv.cn|www.qqqq.org.cn|www.qqrgb.cn|www.qqsevice.com.cn|www.qqsp09.cn|www.qqtrrt.com.cn|www.qqtxvip9.com|www.qqupm.cn|www.qqv0088.com.cn|www.qqvip.org|www.qqvip009.com|www.qqvip2.cn|www.qqvip8.com|www.qqvong.cn|www.qqvotel.cn|www.qqxs6.cn|www.qqxuy.cn|www.qqzock.cn|www.qs5.info|www.qtdyf.cn|www.qtq61.cn|www.qubobo.com|www.quchagg.cn|www.qw2009k.cn|www.qxzpc.cn|www.qyule.com|www.qzswt.cn|www.rayli.com.cn|www.rbrtys.com|www.rtabc.com|www.rtyshu.com|www.sao30.com|www.sao345.com|www.sao48.com|www.sao66.com|www.sao6666.com|www.sao86.com|www.sao9999.com|www.sapag.com.cn|www.sc7w.cn|www.scw666.com|www.scw678.cn|www.sdo.ms|www.se11.info|www.se11se.net|www.se13se.com|www.se14se.net|www.se52se.com|www.securitydot.net|www.securitytestinternetguide.com|www.selaoda.us|www.sese.la|www.sese12.cn|www.sese33.info|www.sesemao.net|www.seseoo.cn|www.seset.com|www.setao.info|www.seuge.com|www.sexxse.com|www.sfnnn.com|www.shagua.cn|www.shangav.com|www.shanpao.com|www.she58.com|www.shipin39.cn|www.shouji56.com|www.shsmg.com.cn|www.sinasj9.com|www.siwamm.net.cn|www.slwyt.com|www.slwyt.info|www.slwyt.org.cn|www.so11.cn|www.so8.zj.cn|www.socks5service.cn|www.softii.com|www.sounet-vip.com|www.sowang123.cn|www.sowang123.com|www.spafeixin.cn|www.sportsbay.cn|www.sporttoday.cn|www.sqwuyt.com|www.sqwyt.cc|www.sqwyt.com|www.ss256.com.cn|www.ss320.cn|www.ss52ss.com|www.ss52ss.net|www.ssdao.com|www.sseae.com|www.sshsex.info|www.taobao359009580.com|www.tb0102-vip.cn|www.tengxun-2009qq.cn|www.tevnet.org|www.thestop.com.cn|www.tianqiyubao.net|www.tiansha.net|www.tingtingjidi.com|www.tk718.com|www.tlwg5.cn|www.top000.com|www.top12.cn|www.tse99.info|www.tskscn.com|www.ttbt.cn|www.ttqsw.com|www.tts8.com|www.ttsou.cn|www.ttwd.net|www.ttzhibo.net|www.tu04.com|www.tu4141.com|www.tu4545.com|www.tu4849.com|www.tube1820.com|www.tvfans8.cn|www.tw18.com|www.tw789.com|www.tx-qq.com|www.txfdqq.cn|www.txqq100000-vip.cn|www.txqq2009vipx.cn|www.txtxz.com|www.u87.cn|www.u88.cn|www.u88.com|www.uee8.cn|www.ugcn.cn|www.usa-yabao.com|www.uu500.com|www.v2233.com|www.v232.com|www.v3338.com|www.vip-cctv2.com|www.vip.qq.wz12.com|www.vnet6.com|www.vpn318.cn|www.vv98mm.cn|www.vymar.cz|www.vytf.com.cn|www.w23q.cn|www.waga.com|www.wan886.com|www.wanqq.net|www.wanwa.com|www.warcraftehina.com|www.warcrnftchina.com|www.wawz.net|www.web6699.cn|www.weblian.cn|www.weiboo.com|www.whsmg.cn|www.winn88.com|www.wokutu.com|www.wowshe1l.com|www.woyaobobo.com|www.woyaozhi.com|www.wwe8.com|www.wymfw.cn|www.wz123.com|www.x.mymirx.cn|www.x0gko.cn|www.x365x.com|www.x365x.net|www.x5-qq.com|www.xdnice.com|www.xg189.net|www.xginfo.com|www.xiaoneirt.com|www.xingfu5yt.info|www.xinkeor.cn|www.xiuseba.com|www.xivod.com|www.xizlang.cn|www.xj001.net|www.xp580.com|www.xp666.com|www.xpstzx.cn|www.xtrades.com.cn|www.xuansf.com|www.xxxsrg.cn|www.xzwrn.cn|www.yahoo-09yh.cn|www.yanmenzao.cn|www.yaobobo.net.cn|www.yaolihui.cn|www.yaosese.com|www.ycdy.com|www.yigeav.com|www.yipu.net|www.ylawfirm.com.cn|www.yo19.cn|www.you2000.cn|www.you2000.net|www.youku.com-09.info|www.youkurt.com|www.youxi668.com|www.yu171.cn|www.yuhaiwei.cn|www.yule918.cn|www.yulewyt.cn|www.yy2000.net|www.yybobo.com|www.yyliao.net|www.yzzfs.cn|www.z360.net|www.z900.info|www.zhangxiu.com|www.zhangyk.org|www.zhaohao5.com|www.zhaomeimei.cn|www.zhaoxf.com|www.zhaoxp.com|www.zhcw889.com|www.zhidao.la|www.zhongjz.com|www.zhuxian2vip.com.cn|www.zigma.hk|www.znwork.com|www.zolcorp.com|www.zuimeng.net|www.zxcai.com|www.zz63.com|www.zz8888.net|www.zzt1.com|www.zzzz1.com|www1-qq.cn|www2.mmmba.com|www6.hejians.cn|wwwitems.cn|wwww.6mxx.com|wylf5168.web013.boothost.com|wys.ooaass.com|wyt09.com|x.beijingxx6.com|x.mymirx.cn|x1ou.3322.org|x5-qq.com|xc.beijingxx7.com|xdl.uhfes.com|xdRexaS.q2bz.cn|xdxd918.3322.org|xia.23u.info|xin110.kmip.net|xinx02.cn|xiny69.cn|xizlang.cn|xl.974671.com|xlxn.eu.kz|xm1.bnz8.cn|xo1u.3322.org|xp.369wyt.com|xp.com|xp.kanwc.com|xp.wyt09.com|xtjdm1.kmip.net|xuk.ru|xx.baidu369.cn|xx.use110.mobi|xxll222.3322.org|xxx|xxx.66qsw.com|xxxsrg.cn|X夜激情|y5fgb.cn|y83.com|ya-tracker.com|yahoo-09yh.cn|yahoo.hd-2009.com.cn|yahoo.mzccf.cn|yaoyao52Ogg.com|yeyeyye.yo2.cn|ygfc1.cn|yia.dns0755.net|yigeav.com|yij.dns0755.net|ynasd.cn|yo19.cn|yof6.cn|yourmusic.count.xj.cn|yourweb.count.xj.cn|youryear.count.xj.cn|yrdsaaw.3322.org|ytveg.cn|ytvmtvgtvitv.com|yu171.cn|yuhaiwei.cn|yuioyui.fr6.hai51.com|yulewyt.cn|yuna133.3322.org|yy.dkjf45.com.cn|yy.douawan.com|yybbbb.com|yyhaomm.cn|yzzfs.cn|z.pstang.com|z360.net|z900.info|zdan84115.xx108.zgsj.net|zgunion.cn|zhcw889.com|zhengjian|zhenshanen|zhuanfalu|zhuanfalun|zhuxian2vip.com.cn|zndxyey.8866.org|zshack.cn|zt.jw961.cn|zxcai.com|zxian.vz.to|zzzz1.com|[av]|[sm]|一中一台|一党|一夜情|一本道|一边一国|一黨|丁字裤|七宗罪|万润南|万维读者论坛|万能钥匙|三个代表|三個代表|三唑仑|三浦愛佳|三级片|三陪|上床|上海孤儿院|上海帮|下体|专制|专政|世界经济导报|东北独立|东南西北论谈|东土耳其斯坦|东方时空|东方红时空|东社|东突|东突厥斯坦伊斯兰运动|东突厥斯坦依斯兰运动|东西南北论坛|丝袜写真|丝袜美腿|两个中国|两会报道|两会新闻|两岸三地论坛|两岸关系|两性淫乱|两性狂情|个人崇拜|中俄|中俄边界新约|中共中央|中华民国|中华民国 造反|中国共产党|中国国家领导人子女任职名单|丰乳|主席|义解|九成新车|九评共产党|习大大|习近平|买卖枪支|买春堂|乱交|乱伦|乳房|争鸣论坛|事实独立|二奶大赛|云雨|亚热|亦凡|人工少女|人权|人民之声论坛|人民内情真相|人民大众时事参考|人民报讯|人民真实|人民真实报导|人民真实报道|他吗的|他妈|他妈的|伊東|伍凡|伦公|伦功|伦攻|伪装美女|余英时|佛展千手法|作爱|你他妈|你妈逼|你敢上吗|你说我说论坛|佳静安定片|侦探设备|保钓|信用危机|修炼|倪育贤|倫公|倫功|倫攻|做爱|做鸡|偷情|偷拍|偷窥|偷窥有罪|催情水|催情药|傻B|傻逼|僵贼|僵贼民|充气娃娃|入珠环|全国两会|全国二奶|八老|公关|公证局|六合彩|六合采|六四事件|六月联盟|共产党|共党|共军|共匪|共狗|共铲党|兽交|内射|军火|军长发威|冰毒|冰火|凌辱|凌锋|出售假币|出售冰毒|出售手枪|出售枪支|分家在|分裂|刑警|则民|办理文凭|办证|功友|功德圆满|功法|加我|动乱|劳动教养所|劳改|包夜|包娃衣|北美自由论坛|北韩|十八禁|升天|升达|升达毕业证|华岳时事论坛|华语世界论坛|华通时事论坛|卖国|南大自由论坛|博彩|博讯|印尼伊斯兰祈祷团|印尼依斯兰祈祷团|压迫|及川奈央|双十节|双峰|反人类|反共|反华|反宇宙|反封锁技术|反攻|反政府|反社会|反雷达测速|反革命|发仑|发伦|发囵|发抡|发正念|发沦|发轮|发轮功|变声器|变声电话|变态|变频棒|口交|口技|古怪歌|古方迷香|叫床|叫春|台湾|台湾建国运动组织|台湾政论区|台湾狗|台湾独立|台湾自由联盟|台湾青年独立联盟|台独|台獨|台盟|右派|叶子楣|司徒华|司法警官|司马晋|司马璐|同居社区|同志用品|同房|吐血|吕京花|吕秀莲|吳邦國|吴仁华|吴学灿|吴学璨|吴宏达|吴官正|吴弘达|吴方城|吴琼花问|吴百益|吴邦国|吸储|吸儲|吸血莱恩|吹萧|吾尔开希|周本顺|周永康|哥疼|哥痛|哲民|唐人电视台|唐捷|唐柏桥|啄木鸟公司|善恶有报|喷尿|喷精|喷血|四川独立|四种当|回民暴动|国军|国家安全|国家机密|国研新闻邮件|国贼|圆满|在十月|地下先烈|地下刊物|地下教会|地磅仪|坐交|坐台|基友|增值税|声色场所|处女|夕樹舞子|外交与方略|外交论坛|多维|多黨|夜勤病栋|夜总会|夜激情|夜總會|夜话紫禁城|大 纪元|大中华论坛|大中国论坛|大众真人真事|大史纪|大史记|大学骚乱|大法|大法弟子|大澤惠|大盖帽|大禁|大纪元新闻网|大纪园|大蓋帽|大鸡巴|天安门事件|天怒|天葬|天鹅之旅|太子党|夫妻性事|央视内部晚会|失意药|失身|套牌|套牌车|女优|女優|女友|女女|妈个|妓女|妖|妹疼|妹痛|姐疼|姐痛|姚月谦|姜春云|姬胜德|娇嫩欲滴|婊子|媽個|嫖妓|嫖娼|嫖鸡|子女任职名单|学潮|学生妹|学联|学自联|学运|宇明网|安定片|宏 志|宏法|官商勾结|密穴|对日强硬|封从德|封杀|射精|将则民|專制|專政|尉健行|小三|小参考|小姐|小泽圆|小泽玛莉亚|小澤園|小电影|小穴|少儿不宜|少儿勿入|少女峰|少女高潮|少妇|尤权|尹庆民|尾行|屁眼|屙民|屠杀|岳武|川島和津實|工自联|巨波|帝国之梦|干他|干你|干她|干柴烈火|幼交|幼齿|广安事件|广闻|庆红|应召|应招|底裤|康师傅|廉政大论坛|建国党|开放杂志|开苞|异见人士|异议人士|弘法|弟子|弟疼|弟痛|张五常|张小平|张志清|张昭富|張丕林|張五常|弹劾|强奸|强效失意药|强暴|强歼|强硬发言|彭州石化|性事|性交|性免费电影|性感|性感内衣裤|性感扑克|性感沙滩|性感猫装|性欲|性游戏|性爱|性爱电影|性福人生|性网站|性虐待|性高潮|恐怖牢笼|恐惧杀手|恶搞晚会|情妇|情欲|情色|惊悚空间|慎入|慰安妇|戈扬|成人图片|成人小说|成人影视|成人文学|成人杂志|成人漫画|成人激情|成人片|成人电影|成人网站|成人论坛|成人贴图|成人配色|我操你妈|手机复制|手机游戏|手机监听器|手机跟踪|手机铃声|手机铃声下载|打倒|打炮|打飞机|找女友|找小三|投毒杀人|抗议|抡功|抢粮记|护士24点|护法|拉丹|拉斯维加斯|拉登|拉登熟知中国|拍肩神药|招工|拦截器|择民|持不同政见|按摩棒|捅你|捅我|捆绑|换妻|探测狗|推翻|插|插B|插他|插你|插她|插我|插插|揭批书|援交|搞日|摇头丸|操B|操你|操你奶奶|操你妈|政变|政府|政治运动|政治风波|教徒|文字狱|文字獄|文革|斐得勒|斯诺|新华举报|新华内情|新华通论坛|新唐人|新唐人电视台|新生网|新疆独立|新疆獨立|新观察论坛|新语丝|新闻封锁|方舟子|於幼軍|无毛|无界|无界浏览|无界浏览器|无网界|无网界浏览|无能|日B|日逼|时事论坛|时代论坛|明慧|明慧网|昏药|易丹轩|易志熹|星崎未來|春夏自由论坛|春宵|春药|暴乱|暴亂|暴动|暴動|暴干|曝光王|書記|曾培炎|曾庆红|曾慶紅|朝河蘭|木子论坛|木犀地|本拉登|朱容基|朱鎔基|朱镕基|杀死|杂志|李克强|李大师|李岚清|李嵐清|李录|李志绥|李总理|李总统|李旺阳|李春城|李月月鸟|李洪宽|李洪志|李淑娴|李瑞环|李瑞環|李登辉|李红痔|李继耐|李远哲|李遠哲|李長春|杜宪|杜智富|极景|林慎立|林樵清|枪决女犯|枪决现场|枪支|枪支弹药|柴老师|柴静|校园魔神|梁光烈|梁擎墩|櫻井|欲望|欲望之血|欲望格斗|欲罢不能|歌功颂德|正义党论坛|正法|正见网|武腾兰|武藤兰|武藤蘭|武警|死亡日志|死亡笔记|毛一鲜|毛主席复活|毛厕洞|毛泽东|毛片|毛贼东|民主墙|民意论坛|民族矛盾|民猪|民联|民运|民进党|民運|民阵|水扁|江coe|江主席|江八点|江则民|江姐问|江戏子|江折民|江氏|江泽慧|江泽民|江流氓|江澤民|江独裁|江猪|江猪媳|江绵恒|江罗|江贼|江贼民|江青|汤光中|汽车爆炸案现场|沉默杀手|沦公|沦功|沦攻|河殇|法 轮 功|法仑|法伦|法倫|法囵|法抡|法沦|法淪|法纶|法論|法论|法輪|法轮|法轮功|法轮大法|波动少女|波波|波霸|泽民|洗脑|洪传|洪吟|洪哲胜|洪志|洪智|流亡|消防隊|淪公|淪功|淪攻|淫书|淫乱|淫乱电影|淫奸|淫水|淫秽|淫荡|淫虫|淫靡|淫魔|温元凯|温家宝|游行|溫家寶|滕文生|潘国平|潮吹|激情图片|激情小电影|激情电影|激情美女|激情自拍|激情视频|炒股国歌|炼功|热比娅|热站政论网|热辣|無修正|熊炎|熊焱|爽死|特码|狂干|狂插|狂搞|狂操|独夫|独立台湾会|独裁|猛插|猛料|猫肉|玉蒲团|瑞士金融大学|瓜娃子|由喜贵|电子狗|电视台|电视流氓|电话拦截|电车之狼|男女自慰用品|疆独|疆独藏独|疆獨|痴汉是犯罪|登辉|發倫|發淪|發論|發輪|白立朴|百家乐|百度搜索顺哥商城领取20元，免费抽苹果6|监听器|监听宝|监听王|盘古乐队|盛华仁|盛雪|看中国|真 善 忍|真善忍|真善美|真相|真象|睾丸|短信群发器|石戈|破处|示威|祁建|神通加持法|禁书|禁区|禁忌试玩|禁片|私处|私服|秘密潜入|秦晋|积克馆|税力|穹顶之下|空难|空難|突厥斯坦|窃听器|窃听器材|童屹|简鸿章|粉饰太平|粗口歌|精液|紅志|紅智|素女|红志|红智|红河谷论坛|红灯区|红色恐怖|约吗|约炮|统战|统独|统独论坛|绳虐|维吾尔自由运动|维多利亚包|绵恒|网特|罗干|罗礼诗|羅幹|美国之音|美国参考|美女视频|美女野球拳|美少女|美少女麻雀|美腿|美腿写真|美臀|群交|群体事件|群发器|群射|翁虹|老子|老毛|老虎机|耽美|联总|联总之声传单|肉棍|肉棒|肉欲|肉洞|肏|肖强|肛交|肛门|胡平|胡总书记|胡景涛|胡温怒批政法系统|胡紧掏|胡錦濤|胡锦涛|胡锦淘|胡锦滔|胸罩|胸部|脱光|脱衣|脱衣舞|腐敗|自制手枪|自慰|自拍|自摸|自杀手册|自杀指南|自焚|自由亚州|自由亚洲|自由运动|臭作|臺灣獨立|舆论|舆论反制|舒淇|舔|色友|色情|色情图片|色情小电影|色情服务|色情电视|色情网站|色欲|色狼|色狼网|色诱|艳舞|艹|花花公子|苏树林|英语枪手|荡妇|莫伟强|菱恝|董存瑞问|葳独|蒙古独立|蒙汗药|蒙汗药粉|蒙独|蒲团|蔡崇国|薛伟|藏春阁|藏独|藏獨|虎胆雄心|虐待|血腥图片|行房|被虐|被逼|裸体|裸女对对碰|裸聊|西藏天葬|西藏独立|西藏獨立|調教|論公|論功|論攻|许家屯|论攻|诱惑|调情|谢中之|谢选骏|谢长廷|豁人|貓肉|賈慶林|贞操|贞操带手铐|贯通两极法|费良勇|贼民|贾庆林|贾廷安|贾育台|赌博|赌博专用|赌博粉|赌徒|赤化|赤匪|赤裸|走光|跳蛋|身份证生成|身份证生成器|輪公|輪功|輪攻|转法|转法轮|轮公|轮功|轮大|轮奸|轮攻|轻舟快讯|辛灏年|边界新约|达赖|近親|远志明|远程偷拍|连胜德|迟浩田|迪里夏提|迫害|迫害法轮功|迷奸药|迷幻药|迷昏药|迷歼药|迷药|迷魂药|迷魂香|退党|透视器|透视照片|透视眼镜|造反|造爱|連發|避孕套|邓笑贫|邝锦文|邢铮|邪恶|邵家健|郑义|鄧小平|酱猪媳|酷匠网|采花堂|野性|金尧如|金澤文子|针孔摄像|钓鱼岛|钦本立|钱其琛|钱庄|钱达|铃声下载|销魂|锦涛|镇压|長瀨愛|阎明复|防拍器|阳具|阴唇|阴囊|阴户|阴毛|阴茎|阴蒂|阴道|阴门|陆委会|陈一谘|陈小同|陈希同|陈总统|陈水扁|陈破空|隐形喷剂|隐形镜|隐私|隐私1图片|雪山狮子|雾霾|震动棒|露乳|露拉3D|露毛|露点|静坐|非典|韩联潮|项小吉|项怀诚|领导财产公示|颜射|風花|风雨神州论坛|风骚|飞扬论坛|飯島愛|饭岛爱|首長|騙局|马会|马大维|马来顺|骗|骗子|骗纸|骚|高干|高干子女|高干子弟|高幹|高文谦|高校暴乱|高校群体事件|高校骚乱|高潮|高自联|高薪养廉|鬼村|魏京生|魏新生|鸡巴|鸡毛信文汇|鸽派|麻古|麻醉乙醚|麻醉枪|麻醉药|麻醉迷幻极品|麻醉钢枪|黃菊|黃麗滿|黄挖挖|黄新初|黄海暗杀事件|黄祸|黄色图片|黄色小电影|黄色小说|黄色影视|黄色文学|黄色漫画|黄色电影|黄色网站|黄菊|黎阳评|黑社会|黑社會|龙卷风|龙虎豹|龟头").matcher("发法轮功法轮大法好").find());
		/*String tempUrl = "http://wximg.gtimg.com/shake_tv/shaketv_new/zip/8sir6b284od1h629gqjts/wx.html";
		System.out.println(tempUrl);
		if (tempUrl.indexOf("wximg.gtimg.com") > 0)
			tempUrl = tempUrl.replaceAll("wximg.gtimg.com", "yaotv.qq.com");
		System.out.println(tempUrl);*/
		
		/*String a = "{\"errcode\":40001,\"errmsg\":\"invalid credential, access_token is invalid or not latest hint: [5g53ia0284vr38!]\"}";
		byte[] datas = a.getBytes();
		//String b = new String(datas);
		System.out.println(new String(datas));
		
		*/
		//System.out.println(DateTimeUtil.getDateTimeString("yyyy.MM.dd HH:mm"));
		
		/*String touserOpenid="oL9SkwyMhqxeeDEA0N_-I6C5TNRU"; 
		String priseName="陈智博";
		String albumTitle="烤翅面包碗";
		String templateId ="LP2aGdDmVmHXQBG1R_u6F3Yr3L2VzjX50-QTxYw40Ws";
		String firstData = "@priseName喜欢了你的相册《@albumTitle》";
		String keyword1Data = "@priseName";
		String keyword2Data = "yyyy.MM.dd HH:mm";
		String remarkData = "点击查看详情";
		String jumpUrlData = "http://statics.holdfun.cn/photo/index.html#!/myLikes";
		String priseTime = DateTimeUtil.getDateTimeString(keyword2Data);
		
		//@priseName喜欢了你的相册《@albumTitle》
				JSONObject firstdataJson = new JSONObject();
				firstdataJson.put("value", firstData.replaceAll("@priseName", priseName).replaceAll("@albumTitle", albumTitle));
				firstdataJson.put("color", "#173177");
				
				//@priseName
				JSONObject keyword1dataJson = new JSONObject();
				keyword1dataJson.put("value", keyword1Data.replaceAll("@priseName", priseName));
				keyword1dataJson.put("color", "#173177");
				
				//yyyy.MM.dd HH:mm
				JSONObject keyword2dataJson = new JSONObject();
				keyword2dataJson.put("value", priseTime);
				keyword2dataJson.put("color", "#173177");
				
				//点击查看详情
				JSONObject remarkdataJson = new JSONObject();
				remarkdataJson.put("value", remarkData);
				remarkdataJson.put("color", "#173177");
				
				JSONObject dataJson = new JSONObject();
				dataJson.put("first", firstdataJson);
				dataJson.put("keyword1", keyword1dataJson);
				dataJson.put("keyword2", keyword2dataJson);
				dataJson.put("remark", remarkdataJson);

				JSONObject messageJson = new JSONObject();
				messageJson.put("touser", touserOpenid);
				messageJson.put("template_id", templateId);
				messageJson.put("url", jumpUrlData);
				messageJson.put("data", dataJson);
				
				System.out.println(messageJson.toString());
				
				String resp = HttpUtil.sendHttpPostReqToServerByReqbody("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=vVClEb7WTxqlTp6CRaYrnrrRKhlyoeDN7-sq3LLboGUnjseu1TpfEme7iFr3t9hyICf68md_KWvBarA147bO2CTRg55gaRKevsVGS6KlahY3deRnLvYDzPEKC4rjgRXXVWQdAFAKLF", messageJson.toString(), null);
				System.out.println(resp);*/
		
		/*String nickName = "%E9%A1%BA%E7%A6%8F+%E7%94%B5%E5%8A%A8%E8%BD%A6%E5%BA%93%E9%97%A8%EF%BC%8C%E4%BF%9D%E6%B8%A9%E5%8D%B7%E5%B8%98%E9%97%A8%EF%BC%8C%E4%BC%B8%E7%BC%A9%E9%97%A8%EF%BC%8C%E6%84%9F%E5%BA%94%E9%97%A8%EF%BC%8C%E9%93%9C%E9%97%A8%EF%BC%8C%E4%B8%8D%E9%94%88%E9%92%A2%E6%89%B6%E6%89%8B%E6%8A%A4%E7%AA%97%E3%80%81";
		try {
			//nickName = nickName.replaceAll("%", "%25");
			System.out.println(nickName);
			
			System.out.println(URLDecoder.decode(nickName, "UTF-8"));
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}*/
		
		/*String str = "wx8fb1145c388915d4";
//		String pattern = "^[A-Za-z0-9]{18,20}$";

//		Pattern r = Pattern.compile(pattern);
		Matcher m = APPID_VALID_PATTERN.matcher(str);
		System.out.println(m.matches());*/
		
		/*String playDateTemp = "01.01";// 日期
		playDateTemp = playDateTemp.replaceAll("\\.", "-");
		System.out.println(playDateTemp);
		
		String current = DateTimeUtil.getDateString();
		System.out.println("current= " + current);
		String dateTemp = String.valueOf(DateTimeUtil.getCurrentYear()) + "-" + playDateTemp;
		System.out.println("dateTemp= " + dateTemp);
		System.out.println("current.compareTo(dateTemp)= " + dateTemp.compareTo(current));
		if(current.compareTo(dateTemp) > 0){
			dateTemp = String.valueOf(DateTimeUtil.getCurrentYear()+1) + "-" + playDateTemp;
		}
		System.out.println(dateTemp);*/
		
		//System.out.println(0 / 1000L);
		/*for(int i=0;i<30;i++)
			System.out.println(UuidUtil.randomUUID());*/
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sortTotalStatistics(JSONArray list, String sortField) {
		// 降序排列
		Comparator mycmp = ComparatorUtils.reversedComparator(ComparatorUtils.naturalComparator());
		Comparator cmp = new BeanComparator(sortField, mycmp);
		Collections.sort(list, cmp);
	}
	
	private static String cleanProtocol4ResDomain(String resDomain) {
		if (resDomain.startsWith("https"))
			return resDomain.replaceFirst("https://", "");
		else
			return resDomain.replaceFirst("http://", "");
	}
	
	public static String analyzeResourceDomain(String currentUrl) {
		if (StringUtils.isEmpty(currentUrl))
			return null;
		// http://yaotv.qq.com/shake_tv/auto/8r870ziaqkbmyi/index.html
		int htmlLength = currentUrl.indexOf(".html");
		if (htmlLength <= 0)
			return null;

		// http://yaotv.qq.com/shake_tv/auto/8r870ziaqkbmyi/index
		String tempUrl = currentUrl.substring(0, htmlLength);

		// http://yaotv.qq.com/shake_tv/auto/8r870ziaqkbmyi
		return tempUrl.substring(0, tempUrl.lastIndexOf("/"));
	}
	
	private static final Random LOTTERY_HIT_RANDOM = new Random();
	
	private static void print(File f,List<String> pl,FilenameFilter filter){
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for(File l : files){
				print(l, pl, filter);
			}
		} else {
			String s = f.getPath();
			if (s.indexOf("WriteMapper.xml") > 0) {
				s = f.getParent();
				// s =
				// s.replace("D:/Workspaces/MyEclipse Professional/zq-tv-core/src/main/resources/",
				// "");
				s = s.replace("D:\\Workspaces\\MyEclipse Professional\\zq-tv-core-bigparty\\src\\main\\resources\\", "");
				s = s.replaceAll("\\\\", "/").concat("/").concat(f.getName());
				s = "<mapper resource=\"" + s + "\" />";
				if (!pl.contains(s)) {
					pl.add(s);
					System.out.println(s);
				}
			}
			// System.out.println(f.getAbsolutePath());
		}
	}
	
	/*public boolean isValidLotteryDuring() {
		Date now = DateTimeUtil.nowDate();
		// 未开始
		if (DateTimeUtil.toDate(playDate, startTime).after(now))
			return false;
		// 已过期
		if (DateTimeUtil.toDate(playDate, endTime).before(now))
			return false;
		return true;
		
	}*/
	public static int binarySearch(Integer[] srcArray, int des) {
	    int low = 0;
	    int high = srcArray.length - 1;
	    int count = 0;
	    while ((low <= high) && (low <= srcArray.length - 1)
	            && (high <= srcArray.length - 1)) {
	    	++count;
	    	 System.out.println(count);
	        int middle = low + ((high - low) >> 1);
	        if (des == srcArray[middle]) {
	            return middle;
	        } else if (des < srcArray[middle]) {
	            high = middle - 1;
	        } else {
	            low = middle + 1;
	        }
	    }
	    System.out.println(count);
	    return -1;
	}
	
	private static void aaa(String... strs){
		System.out.println(strs.length);
	}

	private static final String SPLITER = "###";
	private static String getRankSelfKey(String serviceNo, String yaoOpenid, String periodUuid) {
		return "integral_rankSelf" + SPLITER + serviceNo + SPLITER + yaoOpenid + SPLITER + periodUuid;
	}
}
