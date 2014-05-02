/*
 * Copyright 2013 Jon Iles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rtfparserkit.rtf;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of all RTF commands.
 */
public enum Command
{
   hex("'", CommandType.Symbol),

   optionalhyphen("-", CommandType.Symbol),

   optionalcommand("*", CommandType.Symbol),

   indexsubentry(":", CommandType.Symbol),

   backslash("\\", CommandType.Symbol),

   nonbreakinghyphen("_", CommandType.Symbol),

   opencurly("{", CommandType.Symbol),

   formula("|", CommandType.Symbol),

   closecurly("}", CommandType.Symbol),

   nonbreakingspace("~", CommandType.Symbol),

   ab("ab", CommandType.Toggle),

   absh("absh", CommandType.Value),

   abslock("abslock", CommandType.Flag),

   absnoovrlp("absnoovrlp", CommandType.Toggle),

   absw("absw", CommandType.Value),

   acaps("acaps", CommandType.Toggle),

   acccircle("acccircle", CommandType.Toggle),

   acccomma("acccomma", CommandType.Toggle),

   accdot("accdot", CommandType.Toggle),

   accnone("accnone", CommandType.Toggle),

   accunderdot("accunderdot", CommandType.Toggle),

   acf("acf", CommandType.Value),

   adeff("adeff", CommandType.Value),

   additive("additive", CommandType.Flag),

   adeflang("adeflang", CommandType.Value),

   adjustright("adjustright", CommandType.Flag),

   adn("adn", CommandType.Value),

   aenddoc("aenddoc", CommandType.Flag),

   aendnotes("aendnotes", CommandType.Flag),

   aexpnd("aexpnd", CommandType.Value),

   af("af", CommandType.Value),

   afelev("afelev", CommandType.Flag),

   afs("afs", CommandType.Value),

   aftnbj("aftnbj", CommandType.Flag),

   aftncn("aftncn", CommandType.Destination),

   aftnnalc("aftnnalc", CommandType.Flag),

   aftnnar("aftnnar", CommandType.Flag),

   aftnnauc("aftnnauc", CommandType.Flag),

   aftnnchi("aftnnchi", CommandType.Flag),

   aftnnchosung("aftnnchosung", CommandType.Flag),

   aftnncnum("aftnncnum", CommandType.Flag),

   aftnndbar("aftnndbar", CommandType.Flag),

   aftnndbnum("aftnndbnum", CommandType.Flag),

   aftnndbnumd("aftnndbnumd", CommandType.Flag),

   aftnndbnumk("aftnndbnumk", CommandType.Flag),

   aftnndbnumt("aftnndbnumt", CommandType.Flag),

   aftnnganada("aftnnganada", CommandType.Flag),

   aftnngbnum("aftnngbnum", CommandType.Flag),

   aftnngbnumd("aftnngbnumd", CommandType.Flag),

   aftnngbnumk("aftnngbnumk", CommandType.Flag),

   aftnngbnuml("aftnngbnuml", CommandType.Flag),

   aftnnrlc("aftnnrlc", CommandType.Flag),

   aftnnruc("aftnnruc", CommandType.Flag),

   aftnnzodiac("aftnnzodiac", CommandType.Flag),

   aftnnzodiacd("aftnnzodiacd", CommandType.Flag),

   aftnnzodiacl("aftnnzodiacl", CommandType.Flag),

   aftnrestart("aftnrestart", CommandType.Flag),

   aftnrstcont("aftnrstcont", CommandType.Flag),

   aftnsep("aftnsep", CommandType.Destination),

   aftnsepc("aftnsepc", CommandType.Destination),

   aftnstart("aftnstart", CommandType.Value),

   aftntj("aftntj", CommandType.Flag),

   ai("ai", CommandType.Toggle),

   alang("alang", CommandType.Value),

   allowfieldendsel("allowfieldendsel", CommandType.Flag),

   allprot("allprot", CommandType.Flag),

   alntblind("alntblind", CommandType.Flag),

   alt("alt", CommandType.Flag),

   animtext("animtext", CommandType.Value),

   annotation("annotation", CommandType.Destination),

   annotprot("annotprot", CommandType.Flag),

   ansi("ansi", CommandType.Encoding),

   ansicpg("ansicpg", CommandType.Encoding),

   aoutl("aoutl", CommandType.Toggle),

   ApplyBrkRules("ApplyBrkRules", CommandType.Flag),

   ascaps("ascaps", CommandType.Toggle),

   ashad("ashad", CommandType.Toggle),

   asianbrkrule("asianbrkrule", CommandType.Flag),

   aspalpha("aspalpha", CommandType.Toggle),

   aspnum("aspnum", CommandType.Toggle),

   astrike("astrike", CommandType.Toggle),

   atnauthor("atnauthor", CommandType.Destination),

   atndate("atndate", CommandType.Destination),

   atnicn("atnicn", CommandType.Destination),

   atnid("atnid", CommandType.Destination),

   atnparent("atnparent", CommandType.Destination),

   atnref("atnref", CommandType.Destination),

   atntime("atntime", CommandType.Destination),

   atrfend("atrfend", CommandType.Destination),

   atrfstart("atrfstart", CommandType.Destination),

   aul("aul", CommandType.Toggle),

   auld("auld", CommandType.Toggle),

   auldb("auldb", CommandType.Toggle),

   aulnone("aulnone", CommandType.Toggle),

   aulw("aulw", CommandType.Toggle),

   aup("aup", CommandType.Value),

   author("author", CommandType.Destination),

   autofmtoverride("autofmtoverride", CommandType.Flag),

   b("b", CommandType.Toggle),

   background("background", CommandType.Destination),

   bdbfhdr("bdbfhdr", CommandType.Flag),

   bdrrlswsix("bdrrlswsix", CommandType.Flag),

   bgbdiag("bgbdiag", CommandType.Flag),

   bgcross("bgcross", CommandType.Flag),

   bgdcross("bgdcross", CommandType.Flag),

   bgdkbdiag("bgdkbdiag", CommandType.Flag),

   bgdkcross("bgdkcross", CommandType.Flag),

   bgdkdcross("bgdkdcross", CommandType.Flag),

   bgdkfdiag("bgdkfdiag", CommandType.Flag),

   bgdkhoriz("bgdkhoriz", CommandType.Flag),

   bgdkvert("bgdkvert", CommandType.Flag),

   bgfdiag("bgfdiag", CommandType.Flag),

   bghoriz("bghoriz", CommandType.Flag),

   bgvert("bgvert", CommandType.Flag),

   bin("bin", CommandType.Value),

   binfsxn("binfsxn", CommandType.Value),

   binsxn("binsxn", CommandType.Value),

   bkmkcolf("bkmkcolf", CommandType.Value),

   bkmkcoll("bkmkcoll", CommandType.Value),

   bkmkend("bkmkend", CommandType.Destination),

   bkmkpub("bkmkpub", CommandType.Flag),

   bkmkstart("bkmkstart", CommandType.Destination),

   bliptag("bliptag", CommandType.Value),

   blipuid("blipuid", CommandType.Destination),

   blipupi("blipupi", CommandType.Value),

   blue("blue", CommandType.Value),

   bookfold("bookfold", CommandType.Flag),

   bookfoldrev("bookfoldrev", CommandType.Flag),

   bookfoldsheets("bookfoldsheets", CommandType.Value),

   box("box", CommandType.Flag),

   brdrart("brdrart", CommandType.Value),

   brdrb("brdrb", CommandType.Flag),

   brdrbar("brdrbar", CommandType.Flag),

   brdrbtw("brdrbtw", CommandType.Flag),

   brdrcf("brdrcf", CommandType.Value),

   brdrdash("brdrdash", CommandType.Flag),

   brdrdashd("brdrdashd", CommandType.Flag),

   brdrdashdd("brdrdashdd", CommandType.Flag),

   brdrdashdot("brdrdashdot", CommandType.Flag),

   brdrdashdotdot("brdrdashdotdot", CommandType.Flag),

   brdrdashdotstr("brdrdashdotstr", CommandType.Flag),

   brdrdashsm("brdrdashsm", CommandType.Flag),

   brdrdb("brdrdb", CommandType.Flag),

   brdrdot("brdrdot", CommandType.Flag),

   brdremboss("brdremboss", CommandType.Flag),

   brdrengrave("brdrengrave", CommandType.Flag),

   brdrframe("brdrframe", CommandType.Flag),

   brdrhair("brdrhair", CommandType.Flag),

   brdrinset("brdrinset", CommandType.Flag),

   brdrl("brdrl", CommandType.Flag),

   brdrnil("brdrnil", CommandType.Flag),

   brdrnone("brdrnone", CommandType.Flag),

   brdroutset("brdroutset", CommandType.Flag),

   brdrr("brdrr", CommandType.Flag),

   brdrs("brdrs", CommandType.Flag),

   brdrsh("brdrsh", CommandType.Flag),

   brdrt("brdrt", CommandType.Flag),

   brdrtbl("brdrtbl", CommandType.Flag),

   brdrth("brdrth", CommandType.Flag),

   brdrthtnlg("brdrthtnlg", CommandType.Flag),

   brdrthtnmg("brdrthtnmg", CommandType.Flag),

   brdrthtnsg("brdrthtnsg", CommandType.Flag),

   brdrtnthlg("brdrtnthlg", CommandType.Flag),

   brdrtnthmg("brdrtnthmg", CommandType.Flag),

   brdrtnthsg("brdrtnthsg", CommandType.Flag),

   brdrtnthtnlg("brdrtnthtnlg", CommandType.Flag),

   brdrtnthtnmg("brdrtnthtnmg", CommandType.Flag),

   brdrtnthtnsg("brdrtnthtnsg", CommandType.Flag),

   brdrtriple("brdrtriple", CommandType.Flag),

   brdrw("brdrw", CommandType.Value),

   brdrwavy("brdrwavy", CommandType.Flag),

   brdrwavydb("brdrwavydb", CommandType.Flag),

   brkfrm("brkfrm", CommandType.Flag),

   brsp("brsp", CommandType.Value),

   bullet("bullet", CommandType.Symbol),

   buptim("buptim", CommandType.Destination),

   bxe("bxe", CommandType.Flag),

   caccentfive("caccentfive", CommandType.Flag),

   caccentfour("caccentfour", CommandType.Flag),

   caccentone("caccentone", CommandType.Flag),

   caccentsix("caccentsix", CommandType.Flag),

   caccentthree("caccentthree", CommandType.Flag),

   caccenttwo("caccenttwo", CommandType.Flag),

   cachedcolbal("cachedcolbal", CommandType.Flag),

   caps("caps", CommandType.Toggle),

   category("category", CommandType.Destination),

   cb("cb", CommandType.Value),

   cbackgroundone("cbackgroundone", CommandType.Flag),

   cbackgroundtwo("cbackgroundtwo", CommandType.Flag),

   cbpat("cbpat", CommandType.Value),

   cchsN("cchs", CommandType.Value),

   cell("cell", CommandType.Symbol),

   cellx("cellx", CommandType.Value),

   cf("cf", CommandType.Value),

   cfollowedhyperlink("cfollowedhyperlink", CommandType.Flag),

   cfpat("cfpat", CommandType.Value),

   cgrid("cgrid", CommandType.Value),

   charrsid("charrsid", CommandType.Value),

   charscalex("charscalex", CommandType.Value),

   chatn("chatn", CommandType.Symbol),

   chbgbdiag("chbgbdiag", CommandType.Flag),

   chbgcross("chbgcross", CommandType.Flag),

   chbgdcross("chbgdcross", CommandType.Flag),

   chbgdkbdiag("chbgdkbdiag", CommandType.Flag),

   chbgdkcross("chbgdkcross", CommandType.Flag),

   chbgdkdcross("chbgdkdcross", CommandType.Flag),

   chbgdkfdiag("chbgdkfdiag", CommandType.Flag),

   chbgdkhoriz("chbgdkhoriz", CommandType.Flag),

   chbgdkvert("chbgdkvert", CommandType.Flag),

   chbgfdiag("chbgfdiag", CommandType.Flag),

   chbghoriz("chbghoriz", CommandType.Flag),

   chbgvert("chbgvert", CommandType.Flag),

   chbrdr("chbrdr", CommandType.Flag),

   chcbpat("chcbpat", CommandType.Value),

   chcfpat("chcfpat", CommandType.Value),

   chdate("chdate", CommandType.Symbol),

   chdpa("chdpa", CommandType.Symbol),

   chdpl("chdpl", CommandType.Symbol),

   chftn("chftn", CommandType.Symbol),

   chftnsep("chftnsep", CommandType.Symbol),

   chftnsepc("chftnsepc", CommandType.Symbol),

   chpgn("chpgn", CommandType.Symbol),

   chhres("chhres", CommandType.Value),

   chshdng("chshdng", CommandType.Value),

   chtime("chtime", CommandType.Symbol),

   chyperlink("chyperlink", CommandType.Flag),

   clbgbdiag("clbgbdiag", CommandType.Flag),

   clbgcross("clbgcross", CommandType.Flag),

   clbgdcross("clbgdcross", CommandType.Flag),

   clbgdkbdiag("clbgdkbdiag", CommandType.Flag),

   clbgdkcross("clbgdkcross", CommandType.Flag),

   clbgdkdcross("clbgdkdcross", CommandType.Flag),

   clbgdkfdiag("clbgdkfdiag", CommandType.Flag),

   clbgdkhor("clbgdkhor", CommandType.Flag),

   clbgdkvert("clbgdkvert", CommandType.Flag),

   clbgfdiag("clbgfdiag", CommandType.Flag),

   clbghoriz("clbghoriz", CommandType.Flag),

   clbgvert("clbgvert", CommandType.Flag),

   clbrdrb("clbrdrb", CommandType.Flag),

   clbrdrl("clbrdrl", CommandType.Flag),

   clbrdrr("clbrdrr", CommandType.Flag),

   clbrdrt("clbrdrt", CommandType.Flag),

   clcbpat("clcbpat", CommandType.Value),

   clcbpatraw("clcbpatraw", CommandType.Value),

   clcfpat("clcfpat", CommandType.Value),

   clcfpatraw("clcfpatraw", CommandType.Value),

   cldel("cldel", CommandType.Flag),

   cldelauth("cldelauth", CommandType.Value),

   cldeldttm("cldeldttm", CommandType.Value),

   cldgll("cldgll", CommandType.Flag),

   cldglu("cldglu", CommandType.Flag),

   clFitText("clFitText", CommandType.Flag),

   clftsWidth("clftsWidth", CommandType.Value),

   clhidemark("clhidemark", CommandType.Flag),

   clins("clins", CommandType.Flag),

   clinsauth("clinsauth", CommandType.Value),

   clinsdttm("clinsdttm", CommandType.Value),

   clmgf("clmgf", CommandType.Flag),

   clmrg("clmrg", CommandType.Flag),

   clmrgd("clmrgd", CommandType.Flag),

   clmrgdauth("clmrgdauth", CommandType.Value),

   clmrgddttm("clmrgddttm", CommandType.Value),

   clmrgdr("clmrgdr", CommandType.Flag),

   clNoWrap("clNoWrap", CommandType.Flag),

   clpadb("clpadb", CommandType.Value),

   clpadfb("clpadfb", CommandType.Value),

   clpadfl("clpadfl", CommandType.Value),

   clpadfr("clpadfr", CommandType.Value),

   clpadft("clpadft", CommandType.Value),

   clpadl("clpadl", CommandType.Value),

   clpadr("clpadr", CommandType.Value),

   clpadt("clpadt", CommandType.Value),

   clspb("clspb", CommandType.Value),

   clspfb("clspfb", CommandType.Value),

   clspfl("clspfl", CommandType.Value),

   clspfr("clspfr", CommandType.Value),

   clspft("clspft", CommandType.Value),

   clspl("clspl", CommandType.Value),

   clspr("clspr", CommandType.Value),

   clspt("clspt", CommandType.Value),

   clshdng("clshdng", CommandType.Value),

   clshdngraw("clshdngraw", CommandType.Value),

   clshdrawnil("clshdrawnil", CommandType.Flag),

   clsplit("clsplit", CommandType.Flag),

   clsplitr("clsplitr", CommandType.Flag),

   cltxbtlr("cltxbtlr", CommandType.Flag),

   cltxlrtb("cltxlrtb", CommandType.Flag),

   cltxlrtbv("cltxlrtbv", CommandType.Flag),

   cltxtbrl("cltxtbrl", CommandType.Flag),

   cltxtbrlv("cltxtbrlv", CommandType.Flag),

   clvertalb("clvertalb", CommandType.Flag),

   clvertalc("clvertalc", CommandType.Flag),

   clvertalt("clvertalt", CommandType.Flag),

   clvmgf("clvmgf", CommandType.Flag),

   clvmrg("clvmrg", CommandType.Flag),

   clwWidth("clwWidth", CommandType.Value),

   cmaindarkone("cmaindarkone", CommandType.Flag),

   cmaindarktwo("cmaindarktwo", CommandType.Flag),

   cmainlightone("cmainlightone", CommandType.Flag),

   cmainlighttwo("cmainlighttwo", CommandType.Flag),

   collapsed("collapsed", CommandType.Flag),

   colnoN("colno", CommandType.Value),

   colorschememapping("colorschememapping", CommandType.Destination),

   colortbl("colortbl", CommandType.Destination),

   cols("cols", CommandType.Value),

   colsrN("colsr", CommandType.Value),

   colsx("colsx", CommandType.Value),

   column("column", CommandType.Symbol),

   colwN("colw", CommandType.Value),

   comment("comment", CommandType.Destination),

   company("company", CommandType.Destination),

   contextualspace("contextualspace", CommandType.Flag),

   cpg("cpg", CommandType.Value),

   crauth("crauth", CommandType.Value),

   crdate("crdate", CommandType.Value),

   creatim("creatim", CommandType.Destination),

   cs("cs", CommandType.Value),

   cshade("cshade", CommandType.Value),

   ctextone("ctextone", CommandType.Flag),

   ctexttwo("ctexttwo", CommandType.Flag),

   ctint("ctint", CommandType.Value),

   ctrl("ctrl", CommandType.Flag),

   cts("cts", CommandType.Value),

   cufi("cufi", CommandType.Value),

   culi("culi", CommandType.Value),

   curi("curi", CommandType.Value),

   cvmme("cvmme", CommandType.Flag),

   datafield("datafield", CommandType.Destination),

   datastore("datastore", CommandType.Destination),

   date("date", CommandType.Flag),

   dbch("dbch", CommandType.Flag),

   defchp("defchp", CommandType.Destination),

   deff("deff", CommandType.Value),

   defformat("defformat", CommandType.Flag),

   deflang("deflang", CommandType.Value),

   deflangfe("deflangfe", CommandType.Value),

   defpap("defpap", CommandType.Destination),

   defshp("defshp", CommandType.Flag),

   deftab("deftab", CommandType.Value),

   deleted("deleted", CommandType.Toggle),

   delrsid("delrsid", CommandType.Value),

   dfrauth("dfrauth", CommandType.Value),

   dfrdate("dfrdate", CommandType.Value),

   dfrmtxtx("dfrmtxtx", CommandType.Value),

   dfrmtxty("dfrmtxty", CommandType.Value),

   dfrstart("dfrstart", CommandType.Value),

   dfrstop("dfrstop", CommandType.Value),

   dfrxst("dfrxst", CommandType.Value),

   dghorigin("dghorigin", CommandType.Value),

   dghshow("dghshow", CommandType.Value),

   dghspace("dghspace", CommandType.Value),

   dgmargin("dgmargin", CommandType.Flag),

   dgsnap("dgsnap", CommandType.Flag),

   dgvorigin("dgvorigin", CommandType.Value),

   dgvshow("dgvshow", CommandType.Value),

   dgvspace("dgvspace", CommandType.Value),

   dibitmap("dibitmap", CommandType.Value),

   disabled("disabled", CommandType.Toggle),

   dn("dn", CommandType.Value),

   dntblnsbdb("dntblnsbdb", CommandType.Flag),

   docmd("do", CommandType.Destination),

   dobxcolumn("dobxcolumn", CommandType.Flag),

   dobxmargin("dobxmargin", CommandType.Flag),

   dobxpage("dobxpage", CommandType.Flag),

   dobymargin("dobymargin", CommandType.Flag),

   dobypage("dobypage", CommandType.Flag),

   dobypara("dobypara", CommandType.Flag),

   doccomm("doccomm", CommandType.Destination),

   doctemp("doctemp", CommandType.Flag),

   doctype("doctype", CommandType.Value),

   docvar("docvar", CommandType.Destination),

   dodhgtN("dodhgt", CommandType.Value),

   dolock("dolock", CommandType.Flag),

   donotembedlingdata("donotembedlingdata", CommandType.Value),

   donotembedsysfont("donotembedsysfont", CommandType.Value),

   donotshowcomments("donotshowcomments", CommandType.Flag),

   donotshowinsdel("donotshowinsdel", CommandType.Flag),

   donotshowmarkup("donotshowmarkup", CommandType.Flag),

   donotshowprops("donotshowprops", CommandType.Flag),

   dpaendhol("dpaendhol", CommandType.Flag),

   dpaendlN("dpaendl", CommandType.Value),

   dpaendsol("dpaendsol", CommandType.Flag),

   dpaendwN("dpaendw", CommandType.Value),

   dparc("dparc", CommandType.Flag),

   dparcflipx("dparcflipx", CommandType.Flag),

   dparcflipy("dparcflipy", CommandType.Flag),

   dpastarthol("dpastarthol", CommandType.Flag),

   dpastartlN("dpastartl", CommandType.Value),

   dpastartsol("dpastartsol", CommandType.Flag),

   dpastartwN("dpastartw", CommandType.Value),

   dpcallout("dpcallout", CommandType.Flag),

   dpcoaN("dpcoa", CommandType.Value),

   dpcoaccent("dpcoaccent", CommandType.Flag),

   dpcobestfit("dpcobestfit", CommandType.Flag),

   dpcoborder("dpcoborder", CommandType.Flag),

   dpcodabs("dpcodabs", CommandType.Flag),

   dpcodbottom("dpcodbottom", CommandType.Flag),

   dpcodcenter("dpcodcenter", CommandType.Flag),

   dpcodescent("dpcodescent", CommandType.Value),

   dpcodtop("dpcodtop", CommandType.Flag),

   dpcolengthN("dpcolength", CommandType.Value),

   dpcominusx("dpcominusx", CommandType.Flag),

   dpcominusy("dpcominusy", CommandType.Flag),

   dpcooffsetN("dpcooffset", CommandType.Value),

   dpcosmarta("dpcosmarta", CommandType.Flag),

   dpcotdouble("dpcotdouble", CommandType.Flag),

   dpcotright("dpcotright", CommandType.Flag),

   dpcotsingle("dpcotsingle", CommandType.Flag),

   dpcottriple("dpcottriple", CommandType.Flag),

   dpcountN("dpcount", CommandType.Value),

   dpellipse("dpellipse", CommandType.Flag),

   dpendgroup("dpendgroup", CommandType.Flag),

   dpfillbgcbN("dpfillbgcb", CommandType.Value),

   dpfillbgcgN("dpfillbgcg", CommandType.Value),

   dpfillbgcrN("dpfillbgcr", CommandType.Value),

   dpfillbggrayN("dpfillbggray", CommandType.Value),

   dpfillbgpal("dpfillbgpal", CommandType.Flag),

   dpfillfgcbN("dpfillfgcb", CommandType.Value),

   dpfillfgcgN("dpfillfgcg", CommandType.Value),

   dpfillfgcrN("dpfillfgcr", CommandType.Value),

   dpfillfggrayN("dpfillfggray", CommandType.Value),

   dpfillfgpal("dpfillfgpal", CommandType.Flag),

   dpfillpatN("dpfillpat", CommandType.Value),

   dpgroup("dpgroup", CommandType.Flag),

   dpline("dpline", CommandType.Flag),

   dplinecobN("dplinecob", CommandType.Value),

   dplinecogN("dplinecog", CommandType.Value),

   dplinecorN("dplinecor", CommandType.Value),

   dplinedado("dplinedado", CommandType.Flag),

   dplinedadodo("dplinedadodo", CommandType.Flag),

   dplinedash("dplinedash", CommandType.Flag),

   dplinedot("dplinedot", CommandType.Flag),

   dplinegrayN("dplinegray", CommandType.Value),

   dplinehollow("dplinehollow", CommandType.Flag),

   dplinepal("dplinepal", CommandType.Flag),

   dplinesolid("dplinesolid", CommandType.Flag),

   dplinewN("dplinew", CommandType.Value),

   dppolycountN("dppolycount", CommandType.Value),

   dppolygon("dppolygon", CommandType.Flag),

   dppolyline("dppolyline", CommandType.Flag),

   dpptxN("dpptx", CommandType.Value),

   dpptyN("dppty", CommandType.Value),

   dprect("dprect", CommandType.Flag),

   dproundr("dproundr", CommandType.Flag),

   dpshadow("dpshadow", CommandType.Flag),

   dpshadxN("dpshadx", CommandType.Value),

   dpshadyN("dpshady", CommandType.Value),

   dptxbtlr("dptxbtlr", CommandType.Flag),

   dptxbx("dptxbx", CommandType.Flag),

   dptxbxmarN("dptxbxmar", CommandType.Value),

   dptxbxtext("dptxbxtext", CommandType.Destination),

   dptxlrtb("dptxlrtb", CommandType.Flag),

   dptxlrtbv("dptxlrtbv", CommandType.Flag),

   dptxtbrl("dptxtbrl", CommandType.Flag),

   dptxtbrlv("dptxtbrlv", CommandType.Flag),

   dpxN("dpx", CommandType.Value),

   dpxsizeN("dpxsize", CommandType.Value),

   dpyN("dpy", CommandType.Value),

   dpysizeN("dpysize", CommandType.Value),

   dropcapliN("dropcapli", CommandType.Value),

   dropcaptN("dropcapt", CommandType.Value),

   ds("ds", CommandType.Value),

   dxfrtext("dxfrtext", CommandType.Value),

   dy("dy", CommandType.Value),

   ebcend("ebcend", CommandType.Destination),

   ebcstart("ebcstart", CommandType.Destination),

   edmins("edmins", CommandType.Value),

   embo("embo", CommandType.Toggle),

   emdash("emdash", CommandType.Symbol),

   emfblip("emfblip", CommandType.Flag),

   emspace("emspace", CommandType.Symbol),

   endash("endash", CommandType.Symbol),

   enddoc("enddoc", CommandType.Flag),

   endnhere("endnhere", CommandType.Flag),

   endnotes("endnotes", CommandType.Flag),

   enforceprot("enforceprot", CommandType.Value),

   enspace("enspace", CommandType.Symbol),

   expnd("expnd", CommandType.Value),

   expndtwN("expndtw", CommandType.Value),

   expshrtn("expshrtn", CommandType.Flag),

   f("f", CommandType.Value),

   faauto("faauto", CommandType.Flag),

   facenter("facenter", CommandType.Flag),

   facingp("facingp", CommandType.Flag),

   factoidname("factoidname", CommandType.Destination),

   fafixed("fafixed", CommandType.Flag),

   fahang("fahang", CommandType.Flag),

   falt("falt", CommandType.Destination),

   faroman("faroman", CommandType.Flag),

   favar("favar", CommandType.Flag),

   fbias("fbias", CommandType.Value),

   fbidi("fbidi", CommandType.Flag),

   fbidis("fbidis", CommandType.Flag),

   fbimajor("fbimajor", CommandType.Flag),

   fbiminor("fbiminor", CommandType.Flag),

   fchars("fchars", CommandType.Destination),

   fcharset("fcharset", CommandType.Value),

   fcs("fcs", CommandType.Value),

   fdbmajor("fdbmajor", CommandType.Flag),

   fdbminor("fdbminor", CommandType.Flag),

   fdecor("fdecor", CommandType.Flag),

   felnbrelev("felnbrelev", CommandType.Flag),

   fetN("fet", CommandType.Value),

   fetch("fetch", CommandType.Flag),

   ffdefres("ffdefres", CommandType.Value),

   ffdeftext("ffdeftext", CommandType.Destination),

   ffentrymcr("ffentrymcr", CommandType.Destination),

   ffexitmcr("ffexitmcr", CommandType.Destination),

   ffformat("ffformat", CommandType.Destination),

   ffhaslistbox("ffhaslistbox", CommandType.Value),

   ffhelptext("ffhelptext", CommandType.Destination),

   ffhps("ffhps", CommandType.Value),

   ffl("ffl", CommandType.Destination),

   ffmaxlen("ffmaxlen", CommandType.Value),

   ffname("ffname", CommandType.Destination),

   ffownhelp("ffownhelp", CommandType.Value),

   ffownstat("ffownstat", CommandType.Value),

   ffprot("ffprot", CommandType.Value),

   ffrecalc("ffrecalc", CommandType.Value),

   ffres("ffres", CommandType.Value),

   ffsize("ffsize", CommandType.Value),

   ffstattext("ffstattext", CommandType.Destination),

   fftype("fftype", CommandType.Value),

   fftypetxt("fftypetxt", CommandType.Value),

   fhimajor("fhimajor", CommandType.Flag),

   fhiminor("fhiminor", CommandType.Flag),

   fi("fi", CommandType.Value),

   fidN("fid", CommandType.Value),

   field("field", CommandType.Destination),

   file("file", CommandType.Destination),

   filetbl("filetbl", CommandType.Destination),

   fittext("fittext", CommandType.Value),

   fjgothic("fjgothic", CommandType.Flag),

   fjminchou("fjminchou", CommandType.Flag),

   fldalt("fldalt", CommandType.Flag),

   flddirty("flddirty", CommandType.Flag),

   fldedit("fldedit", CommandType.Flag),

   fldinst("fldinst", CommandType.Destination),

   fldlock("fldlock", CommandType.Flag),

   fldpriv("fldpriv", CommandType.Flag),

   fldrslt("fldrslt", CommandType.Destination),

   fldtype("fldtype", CommandType.Destination),

   flomajor("flomajor", CommandType.Flag),

   flominor("flominor", CommandType.Flag),

   fmodern("fmodern", CommandType.Flag),

   fn("fn", CommandType.Value),

   fname("fname", CommandType.Destination),

   fnetwork("fnetwork", CommandType.Flag),

   fnil("fnil", CommandType.Flag),

   fnonfilesys("fnonfilesys", CommandType.Flag),

   fontemb("fontemb", CommandType.Destination),

   fontfile("fontfile", CommandType.Destination),

   fonttbl("fonttbl", CommandType.Destination),

   footer("footer", CommandType.Destination),

   footerf("footerf", CommandType.Destination),

   footerl("footerl", CommandType.Destination),

   footerr("footerr", CommandType.Destination),

   footery("footery", CommandType.Value),

   footnote("footnote", CommandType.Destination),

   forceupgrade("forceupgrade", CommandType.Flag),

   formdisp("formdisp", CommandType.Flag),

   formfield("formfield", CommandType.Destination),

   formprot("formprot", CommandType.Flag),

   formshade("formshade", CommandType.Flag),

   fosnum("fosnum", CommandType.Value),

   fprq("fprq", CommandType.Value),

   fracwidth("fracwidth", CommandType.Flag),

   frelativeN("frelative", CommandType.Value),

   frmtxbtlr("frmtxbtlr", CommandType.Flag),

   frmtxlrtb("frmtxlrtb", CommandType.Flag),

   frmtxlrtbv("frmtxlrtbv", CommandType.Flag),

   frmtxtbrl("frmtxtbrl", CommandType.Flag),

   frmtxtbrlv("frmtxtbrlv", CommandType.Flag),

   froman("froman", CommandType.Flag),

   fromhtml("fromhtml", CommandType.Value),

   fromtext("fromtext", CommandType.Flag),

   fs("fs", CommandType.Value),

   fscript("fscript", CommandType.Flag),

   fswiss("fswiss", CommandType.Flag),

   ftech("ftech", CommandType.Flag),

   ftnalt("ftnalt", CommandType.Flag),

   ftnbj("ftnbj", CommandType.Flag),

   ftncn("ftncn", CommandType.Destination),

   ftnil("ftnil", CommandType.Flag),

   ftnlytwnine("ftnlytwnine", CommandType.Flag),

   ftnnalc("ftnnalc", CommandType.Flag),

   ftnnar("ftnnar", CommandType.Flag),

   ftnnauc("ftnnauc", CommandType.Flag),

   ftnnchi("ftnnchi", CommandType.Flag),

   ftnnchosung("ftnnchosung", CommandType.Flag),

   ftnncnum("ftnncnum", CommandType.Flag),

   ftnndbar("ftnndbar", CommandType.Flag),

   ftnndbnum("ftnndbnum", CommandType.Flag),

   ftnndbnumd("ftnndbnumd", CommandType.Flag),

   ftnndbnumk("ftnndbnumk", CommandType.Flag),

   ftnndbnumt("ftnndbnumt", CommandType.Flag),

   ftnnganada("ftnnganada", CommandType.Flag),

   ftnngbnum("ftnngbnum", CommandType.Flag),

   ftnngbnumd("ftnngbnumd", CommandType.Flag),

   ftnngbnumk("ftnngbnumk", CommandType.Flag),

   ftnngbnuml("ftnngbnuml", CommandType.Flag),

   ftnnrlc("ftnnrlc", CommandType.Flag),

   ftnnruc("ftnnruc", CommandType.Flag),

   ftnnzodiac("ftnnzodiac", CommandType.Flag),

   ftnnzodiacd("ftnnzodiacd", CommandType.Flag),

   ftnnzodiacl("ftnnzodiacl", CommandType.Flag),

   ftnrestart("ftnrestart", CommandType.Flag),

   ftnrstcont("ftnrstcont", CommandType.Flag),

   ftnrstpg("ftnrstpg", CommandType.Flag),

   ftnsep("ftnsep", CommandType.Destination),

   ftnsepc("ftnsepc", CommandType.Destination),

   ftnstart("ftnstart", CommandType.Value),

   ftntj("ftntj", CommandType.Flag),

   fttruetype("fttruetype", CommandType.Flag),

   fvaliddos("fvaliddos", CommandType.Flag),

   fvalidhpfs("fvalidhpfs", CommandType.Flag),

   fvalidmac("fvalidmac", CommandType.Flag),

   fvalidntfs("fvalidntfs", CommandType.Flag),

   g("g", CommandType.Destination),

   gcw("gcw", CommandType.Value),

   generator("generator", CommandType.Destination),

   green("green", CommandType.Value),

   grfdocevents("grfdocevents", CommandType.Value),

   gridtbl("gridtbl", CommandType.Destination),

   gutter("gutter", CommandType.Value),

   gutterprl("gutterprl", CommandType.Flag),

   guttersxn("guttersxn", CommandType.Value),

   header("header", CommandType.Destination),

   headerf("headerf", CommandType.Destination),

   headerl("headerl", CommandType.Destination),

   headerr("headerr", CommandType.Destination),

   headery("headery", CommandType.Value),

   hich("hich", CommandType.Flag),

   highlight("highlight", CommandType.Value),

   hl("hl", CommandType.Destination),

   hlfr("hlfr", CommandType.Destination),

   hlinkbase("hlinkbase", CommandType.Destination),

   hlloc("hlloc", CommandType.Destination),

   hlsrc("hlsrc", CommandType.Destination),

   horzdoc("horzdoc", CommandType.Flag),

   horzsect("horzsect", CommandType.Flag),

   horzvert("horzvert", CommandType.Value),

   hr("hr", CommandType.Value),

   hres("hres", CommandType.Value),

   hrule("hrule", CommandType.Flag),

   hsv("hsv", CommandType.Destination),

   htmautsp("htmautsp", CommandType.Flag),

   htmlbase("htmlbase", CommandType.Flag),

   htmlrtf("htmlrtf", CommandType.Toggle),

   htmltag("htmltag", CommandType.Destination),

   hwelev("hwelev", CommandType.Flag),

   hyphauto("hyphauto", CommandType.Toggle),

   hyphcaps("hyphcaps", CommandType.Toggle),

   hyphconsecN("hyphconsec", CommandType.Value),

   hyphhotz("hyphhotz", CommandType.Value),

   hyphpar("hyphpar", CommandType.Toggle),

   i("i", CommandType.Toggle),

   id("id", CommandType.Value),

   ignoremixedcontent("ignoremixedcontent", CommandType.Value),

   ilfomacatclnup("ilfomacatclnup", CommandType.Value),

   ilvl("ilvl", CommandType.Value),

   impr("impr", CommandType.Toggle),

   indmirror("indmirror", CommandType.Flag),

   indrlsweleven("indrlsweleven", CommandType.Flag),

   info("info", CommandType.Destination),

   insrsid("insrsid", CommandType.Value),

   intbl("intbl", CommandType.Flag),

   ipgp("ipgp", CommandType.Value),

   irowband("irowband", CommandType.Value),

   irow("irow", CommandType.Value),

   itap("itap", CommandType.Value),

   ixe("ixe", CommandType.Flag),

   jcompress("jcompress", CommandType.Flag),

   jexpand("jexpand", CommandType.Flag),

   jis("jis", CommandType.Flag),

   jpegblip("jpegblip", CommandType.Flag),

   jsksu("jsksu", CommandType.Flag),

   keep("keep", CommandType.Flag),

   keepn("keepn", CommandType.Flag),

   kerningN("kerning", CommandType.Value),

   keycode("keycode", CommandType.Destination),

   keywords("keywords", CommandType.Destination),

   krnprsnet("krnprsnet", CommandType.Flag),

   ksulang("ksulang", CommandType.Value),

   jclisttab("jclisttab", CommandType.Flag),

   landscape("landscape", CommandType.Flag),

   lang("lang", CommandType.Value),

   langfe("langfe", CommandType.Value),

   langfenp("langfenp", CommandType.Value),

   langnp("langnp", CommandType.Value),

   lastrow("lastrow", CommandType.Flag),

   latentstyles("latentstyles", CommandType.Destination),

   lbr("lbr", CommandType.Value),

   lchars("lchars", CommandType.Destination),

   ldblquote("ldblquote", CommandType.Symbol),

   level("level", CommandType.Value),

   levelfollow("levelfollow", CommandType.Value),

   levelindent("levelindent", CommandType.Value),

   leveljc("leveljc", CommandType.Value),

   leveljcn("leveljcn", CommandType.Value),

   levellegal("levellegal", CommandType.Value),

   levelnfc("levelnfc", CommandType.Value),

   levelnfcn("levelnfcn", CommandType.Value),

   levelnorestart("levelnorestart", CommandType.Value),

   levelnumbers("levelnumbers", CommandType.Destination),

   levelold("levelold", CommandType.Value),

   levelpicture("levelpicture", CommandType.Value),

   levelpicturenosize("levelpicturenosize", CommandType.Flag),

   levelprev("levelprev", CommandType.Value),

   levelprevspace("levelprevspace", CommandType.Value),

   levelspace("levelspace", CommandType.Value),

   levelstartat("levelstartat", CommandType.Value),

   leveltemplateid("leveltemplateid", CommandType.Value),

   leveltext("leveltext", CommandType.Destination),

   lfolevel("lfolevel", CommandType.Destination),

   li("li", CommandType.Value),

   line("line", CommandType.Symbol),

   linebetcol("linebetcol", CommandType.Flag),

   linecont("linecont", CommandType.Flag),

   linemod("linemod", CommandType.Value),

   lineppage("lineppage", CommandType.Flag),

   linerestart("linerestart", CommandType.Flag),

   linestart("linestart", CommandType.Value),

   linestarts("linestarts", CommandType.Value),

   linex("linex", CommandType.Value),

   linkself("linkself", CommandType.Flag),

   linkstyles("linkstyles", CommandType.Flag),

   linkval("linkval", CommandType.Destination),

   lin("lin", CommandType.Value),

   lisa("lisa", CommandType.Value),

   lisb("lisb", CommandType.Value),

   list("list", CommandType.Destination),

   listhybrid("listhybrid", CommandType.Flag),

   listid("listid", CommandType.Value),

   listlevel("listlevel", CommandType.Destination),

   listname("listname", CommandType.Destination),

   listoverride("listoverride", CommandType.Destination),

   listoverridecount("listoverridecount", CommandType.Value),

   listoverrideformat("listoverrideformat", CommandType.Value),

   listoverridestartat("listoverridestartat", CommandType.Flag),

   listoverridetable("listoverridetable", CommandType.Destination),

   listpicture("listpicture", CommandType.Destination),

   listrestarthdn("listrestarthdn", CommandType.Value),

   listsimple("listsimple", CommandType.Value),

   liststyleid("liststyleid", CommandType.Value),

   liststylename("liststylename", CommandType.Destination),

   listtable("listtable", CommandType.Destination),

   listtemplateid("listtemplateid", CommandType.Value),

   listtext("listtext", CommandType.Destination),

   lnbrkrule("lnbrkrule", CommandType.Flag),

   lndscpsxn("lndscpsxn", CommandType.Flag),

   lnongrid("lnongrid", CommandType.Flag),

   loch("loch", CommandType.Flag),

   lquote("lquote", CommandType.Symbol),

   ls("ls", CommandType.Value),

   lsdlocked("lsdlocked", CommandType.Value),

   lsdlockeddef("lsdlockeddef", CommandType.Value),

   lsdlockedexcept("lsdlockedexcept", CommandType.Destination),

   lsdpriority("lsdpriority", CommandType.Value),

   lsdprioritydef("lsdprioritydef", CommandType.Value),

   lsdqformat("lsdqformat", CommandType.Value),

   lsdqformatdef("lsdqformatdef", CommandType.Value),

   lsdsemihidden("lsdsemihidden", CommandType.Value),

   lsdsemihiddendef("lsdsemihiddendef", CommandType.Value),

   lsdstimax("lsdstimax", CommandType.Value),

   lsdunhideused("lsdunhideused", CommandType.Value),

   lsdunhideuseddef("lsdunhideuseddef", CommandType.Value),

   ltrch("ltrch", CommandType.Flag),

   ltrdoc("ltrdoc", CommandType.Flag),

   ltrmark("ltrmark", CommandType.Symbol),

   ltrpar("ltrpar", CommandType.Flag),

   ltrrow("ltrrow", CommandType.Flag),

   ltrsect("ltrsect", CommandType.Flag),

   lvltentative("lvltentative", CommandType.Flag),

   lytcalctblwd("lytcalctblwd", CommandType.Flag),

   lytexcttp("lytexcttp", CommandType.Flag),

   lytprtmet("lytprtmet", CommandType.Flag),

   lyttblrtgr("lyttblrtgr", CommandType.Flag),

   mac("mac", CommandType.Encoding),

   macc("macc", CommandType.Destination),

   maccPr("maccPr", CommandType.Destination),

   macpict("macpict", CommandType.Flag),

   mailmerge("mailmerge", CommandType.Destination),

   makebackup("makebackup", CommandType.Flag),

   maln("maln", CommandType.Destination),

   malnScr("malnScr", CommandType.Destination),

   manager("manager", CommandType.Destination),

   margb("margb", CommandType.Value),

   margbsxn("margbsxn", CommandType.Value),

   margl("margl", CommandType.Value),

   marglsxn("marglsxn", CommandType.Value),

   margmirror("margmirror", CommandType.Flag),

   margmirsxn("margmirsxn", CommandType.Flag),

   margPr("margPr", CommandType.Destination),

   margr("margr", CommandType.Value),

   margrsxn("margrsxn", CommandType.Value),

   margSz("margSz", CommandType.Value),

   margt("margt", CommandType.Value),

   margtsxn("margtsxn", CommandType.Value),

   mbar("mbar", CommandType.Destination),

   mbarPr("mbarPr", CommandType.Destination),

   mbaseJc("mbaseJc", CommandType.Destination),

   mbegChr("mbegChr", CommandType.Destination),

   mborderBox("mborderBox", CommandType.Destination),

   mborderBoxPr("mborderBoxPr", CommandType.Destination),

   mbox("mbox", CommandType.Destination),

   mboxPr("mboxPr", CommandType.Destination),

   mbrk("mbrk", CommandType.Value),

   mbrkBin("mbrkBin", CommandType.Value),

   mbrkBinSub("mbrkBinSub", CommandType.Value),

   mcGp("mcGp", CommandType.Value),

   mcGpRule("mcGpRule", CommandType.Value),

   mchr("mchr", CommandType.Destination),

   mcount("mcount", CommandType.Destination),

   mcSp("mcSp", CommandType.Value),

   mctrlPr("mctrlPr", CommandType.Destination),

   md("md", CommandType.Destination),

   mdefJc("mdefJc", CommandType.Value),

   mdeg("mdeg", CommandType.Destination),

   mdegHide("mdegHide", CommandType.Destination),

   mden("mden", CommandType.Destination),

   mdiff("mdiff", CommandType.Destination),

   mdiffSty("mdiffSty", CommandType.Value),

   mdispDef("mdispDef", CommandType.Value), // case is incorrect in the spec

   mdPr("mdPr", CommandType.Destination),

   me("me", CommandType.Destination),

   mendChr("mendChr", CommandType.Destination),

   meqArr("meqArr", CommandType.Destination),

   meqArrPr("meqArrPr", CommandType.Destination),

   mf("mf", CommandType.Destination),

   mfName("mfName", CommandType.Destination),

   mfPr("mfPr", CommandType.Destination),

   mfunc("mfunc", CommandType.Destination),

   mfuncPr("mfuncPr", CommandType.Destination),

   mgroupChr("mgroupChr", CommandType.Destination),

   mgroupChrPr("mgroupChrPr", CommandType.Destination),

   mgrow("mgrow", CommandType.Destination),

   mhideBot("mhideBot", CommandType.Destination),

   mhideLeft("mhideLeft", CommandType.Destination),

   mhideRight("mhideRight", CommandType.Destination),

   mhideTop("mhideTop", CommandType.Destination),

   mhtmltag("mhtmltag", CommandType.Destination),

   min("min", CommandType.Value),

   minterSp("minterSp", CommandType.Value),

   mintLim("mintLim", CommandType.Value),

   mintraSp("mintraSp", CommandType.Value),

   mjc("mjc", CommandType.Value),

   mlim("mlim", CommandType.Destination),

   mlimloc("mlimloc", CommandType.Destination),

   mlimlow("mlimlow", CommandType.Destination),

   mlimlowPr("mlimlowPr", CommandType.Destination),

   mlimupp("mlimupp", CommandType.Destination),

   mlimuppPr("mlimuppPr", CommandType.Destination),

   mlit("mlit", CommandType.Flag),

   mlMargin("mlMargin", CommandType.Value),

   mm("mm", CommandType.Destination),

   mmaddfieldname("mmaddfieldname", CommandType.Destination),

   mmath("mmath", CommandType.Destination),

   mmathFont("mmathFont", CommandType.Value),

   mmathPict("mmathPict", CommandType.Destination),

   mmathPr("mmathPr", CommandType.Destination),

   mmattach("mmattach", CommandType.Flag),

   mmaxdist("mmaxdist", CommandType.Destination),

   mmblanklines("mmblanklines", CommandType.Flag),

   mmc("mmc", CommandType.Destination),

   mmcJc("mmcJc", CommandType.Destination),

   mmconnectstr("mmconnectstr", CommandType.Destination),

   mmconnectstrdata("mmconnectstrdata", CommandType.Destination),

   mmcPr("mmcPr", CommandType.Destination),

   mmcs("mmcs", CommandType.Destination),

   mmdatasource("mmdatasource", CommandType.Destination),

   mmdatatypeaccess("mmdatatypeaccess", CommandType.Flag),

   mmdatatypeexcel("mmdatatypeexcel", CommandType.Flag),

   mmdatatypefile("mmdatatypefile", CommandType.Flag),

   mmdatatypeodbc("mmdatatypeodbc", CommandType.Flag),

   mmdatatypeodso("mmdatatypeodso", CommandType.Flag),

   mmdatatypeqt("mmdatatypeqt", CommandType.Flag),

   mmdefaultsql("mmdefaultsql", CommandType.Flag),

   mmdestemail("mmdestemail", CommandType.Flag),

   mmdestfax("mmdestfax", CommandType.Flag),

   mmdestnewdoc("mmdestnewdoc", CommandType.Flag),

   mmdestprinter("mmdestprinter", CommandType.Flag),

   mmerrors("mmerrors", CommandType.Value),

   mmfttypeaddress("mmfttypeaddress", CommandType.Flag),

   mmfttypebarcode("mmfttypebarcode", CommandType.Flag),

   mmfttypedbcolumn("mmfttypedbcolumn", CommandType.Flag),

   mmfttypemapped("mmfttypemapped", CommandType.Flag),

   mmfttypenull("mmfttypenull", CommandType.Flag),

   mmfttypesalutation("mmfttypesalutation", CommandType.Flag),

   mmheadersource("mmheadersource", CommandType.Destination),

   mmjdsotype("mmjdsotype", CommandType.Value),

   mmlinktoquery("mmlinktoquery", CommandType.Flag),

   mmmailsubject("mmmailsubject", CommandType.Destination),

   mmmaintypecatalog("mmmaintypecatalog", CommandType.Flag),

   mmmaintypeemail("mmmaintypeemail", CommandType.Flag),

   mmmaintypeenvelopes("mmmaintypeenvelopes", CommandType.Flag),

   mmmaintypefax("mmmaintypefax", CommandType.Flag),

   mmmaintypelabels("mmmaintypelabels", CommandType.Flag),

   mmmaintypeletters("mmmaintypeletters", CommandType.Flag),

   mmodso("mmodso", CommandType.Destination),

   mmodsoactive("mmodsoactive", CommandType.Value),

   mmodsocoldelim("mmodsocoldelim", CommandType.Value),

   mmodsocolumn("mmodsocolumn", CommandType.Value),

   mmodsodynaddr("mmodsodynaddr", CommandType.Value),

   mmodsofhdr("mmodsofhdr", CommandType.Value),

   mmodsofilter("mmodsofilter", CommandType.Destination),

   mmodsofldmpdata("mmodsofldmpdata", CommandType.Destination),

   mmodsofmcolumn("mmodsofmcolumn", CommandType.Value),

   mmodsohash("mmodsohash", CommandType.Value),

   mmodsolid("mmodsolid", CommandType.Value),

   mmodsomappedname("mmodsomappedname", CommandType.Destination),

   mmodsoname("mmodsoname", CommandType.Destination),

   mmodsorecipdata("mmodsorecipdata", CommandType.Destination),

   mmodsosort("mmodsosort", CommandType.Destination),

   mmodsosrc("mmodsosrc", CommandType.Destination),

   mmodsotable("mmodsotable", CommandType.Destination),

   mmodsoudl("mmodsoudl", CommandType.Destination),

   mmodsoudldata("mmodsoudldata", CommandType.Destination),

   mmodsouniquetag("mmodsouniquetag", CommandType.Destination),

   mmPr("mmPr", CommandType.Destination),

   mmquery("mmquery", CommandType.Destination),

   mmr("mmr", CommandType.Destination),

   mmreccur("mmreccur", CommandType.Value),

   mmshowdata("mmshowdata", CommandType.Flag),

   mnary("mnary", CommandType.Destination),

   mnaryLim("mnaryLim", CommandType.Value),

   mnaryPr("mnaryPr", CommandType.Destination),

   mnoBreak("mnoBreak", CommandType.Destination),

   mnor("mnor", CommandType.Flag),

   mnum("mnum", CommandType.Destination),

   mo("mo", CommandType.Value),

   mobjDist("mobjDist", CommandType.Destination),

   moMath("moMath", CommandType.Destination),

   moMathPara("moMathPara", CommandType.Destination),

   moMathParaPr("moMathParaPr", CommandType.Destination),

   mopEmu("mopEmu", CommandType.Destination),

   mphant("mphant", CommandType.Destination),

   mphantPr("mphantPr", CommandType.Destination),

   mplcHide("mplcHide", CommandType.Destination),

   mpos("mpos", CommandType.Destination),

   mpostSp("mpostSp", CommandType.Value),

   mpreSp("mpreSp", CommandType.Value),

   mr("mr", CommandType.Destination),

   mrad("mrad", CommandType.Destination),

   mradPr("mradPr", CommandType.Destination),

   mrMargin("mrMargin", CommandType.Value),

   mrPr("mrPr", CommandType.Destination),

   mrSp("mrSp", CommandType.Value),

   mrSpRule("mrSpRule", CommandType.Value),

   mscr("mscr", CommandType.Value),

   msepChr("msepChr", CommandType.Destination),

   mshow("mshow", CommandType.Destination),

   mshp("mshp", CommandType.Destination),

   msmallFrac("msmallFrac", CommandType.Value),

   msmcap("msmcap", CommandType.Flag),

   msPre("msPre", CommandType.Destination),

   msPrePr("msPrePr", CommandType.Destination),

   msSub("msSub", CommandType.Destination),

   msSubPr("msSubPr", CommandType.Destination),

   msSubSup("msSubSup", CommandType.Destination),

   msSubSupPr("msSubSupPr", CommandType.Destination),

   msSup("msSup", CommandType.Destination),

   msSupPr("msSupPr", CommandType.Destination),

   mstrikeBLTR("mstrikeBLTR", CommandType.Destination),

   mstrikeH("mstrikeH", CommandType.Destination),

   mstrikeTLBR("mstrikeTLBR", CommandType.Destination),

   mstrikeV("mstrikeV", CommandType.Destination),

   msty("msty", CommandType.Value),

   msub("msub", CommandType.Destination),

   msubHide("msubHide", CommandType.Destination),

   msup("msup", CommandType.Destination),

   msupHide("msupHide", CommandType.Destination),

   mtransp("mtransp", CommandType.Destination),

   mtype("mtype", CommandType.Destination),

   muser("muser", CommandType.Flag),

   mvauthN("mvauth", CommandType.Value),

   mvdateN("mvdate", CommandType.Value),

   mvertJc("mvertJc", CommandType.Destination),

   mvf("mvf", CommandType.Flag),

   mvfmf("mvfmf", CommandType.Destination),

   mvfml("mvfml", CommandType.Destination),

   mvt("mvt", CommandType.Flag),

   mvtof("mvtof", CommandType.Destination),

   mvtol("mvtol", CommandType.Destination),

   mwrapIndent("mwrapIndent", CommandType.Value),

   mwrapRight("mwrapRight", CommandType.Value),

   mzeroAsc("mzeroAsc", CommandType.Destination),

   mzeroDesc("mzeroDesc", CommandType.Destination),

   mzeroWid("mzeroWid", CommandType.Destination),

   nestcell("nestcell", CommandType.Symbol),

   nestrow("nestrow", CommandType.Symbol),

   nesttableprops("nesttableprops", CommandType.Destination),

   newtblstyruls("newtblstyruls", CommandType.Flag),

   nextfile("nextfile", CommandType.Destination),

   noafcnsttbl("noafcnsttbl", CommandType.Flag),

   nobrkwrptbl("nobrkwrptbl", CommandType.Flag),

   nocolbal("nocolbal", CommandType.Flag),

   nocompatoptions("nocompatoptions", CommandType.Flag),

   nocwrap("nocwrap", CommandType.Flag),

   nocxsptable("nocxsptable", CommandType.Flag),

   noextrasprl("noextrasprl", CommandType.Flag),

   nofchars("nofchars", CommandType.Value),

   nofcharsws("nofcharsws", CommandType.Value),

   nofeaturethrottle("nofeaturethrottle", CommandType.Flag),

   nofpages("nofpages", CommandType.Value),

   nofwords("nofwords", CommandType.Value),

   nogrowautofit("nogrowautofit", CommandType.Flag),

   noindnmbrts("noindnmbrts", CommandType.Flag),

   nojkernpunct("nojkernpunct", CommandType.Flag),

   nolead("nolead", CommandType.Flag),

   noline("noline", CommandType.Flag),

   nolnhtadjtbl("nolnhtadjtbl", CommandType.Flag),

   nonesttables("nonesttables", CommandType.Destination),

   nonshppict("nonshppict", CommandType.Flag),

   nooverflow("nooverflow", CommandType.Flag),

   noproof("noproof", CommandType.Flag),

   noqfpromote("noqfpromote", CommandType.Flag),

   nosectexpand("nosectexpand", CommandType.Flag),

   nosnaplinegrid("nosnaplinegrid", CommandType.Flag),

   nospaceforul("nospaceforul", CommandType.Flag),

   nosupersub("nosupersub", CommandType.Flag),

   notabind("notabind", CommandType.Flag),

   notbrkcnstfrctbl("notbrkcnstfrctbl", CommandType.Flag),

   notcvasp("notcvasp", CommandType.Flag),

   notvatxbx("notvatxbx", CommandType.Flag),

   nouicompat("nouicompat", CommandType.Flag),

   noultrlspc("noultrlspc", CommandType.Flag),

   nowidctlpar("nowidctlpar", CommandType.Flag),

   nowrap("nowrap", CommandType.Flag),

   nowwrap("nowwrap", CommandType.Flag),

   noxlattoyen("noxlattoyen", CommandType.Flag),

   objalias("objalias", CommandType.Destination),

   objalign("objalign", CommandType.Value),

   objattph("objattph", CommandType.Flag),

   objautlink("objautlink", CommandType.Flag),

   objclass("objclass", CommandType.Destination),

   objcropb("objcropb", CommandType.Value),

   objcropl("objcropl", CommandType.Value),

   objcropr("objcropr", CommandType.Value),

   objcropt("objcropt", CommandType.Value),

   objdata("objdata", CommandType.Destination),

   object("object", CommandType.Destination),

   objemb("objemb", CommandType.Flag),

   objh("objh", CommandType.Value),

   objhtml("objhtml", CommandType.Flag),

   objicemb("objicemb", CommandType.Flag),

   objlink("objlink", CommandType.Flag),

   objlock("objlock", CommandType.Flag),

   objname("objname", CommandType.Destination),

   objocx("objocx", CommandType.Flag),

   objpub("objpub", CommandType.Flag),

   objscalex("objscalex", CommandType.Value),

   objscaley("objscaley", CommandType.Value),

   objsect("objsect", CommandType.Destination),

   objsetsize("objsetsize", CommandType.Flag),

   objsub("objsub", CommandType.Flag),

   objtime("objtime", CommandType.Destination),

   objtransy("objtransy", CommandType.Value),

   objupdate("objupdate", CommandType.Flag),

   objw("objw", CommandType.Value),

   ogutter("ogutter", CommandType.Value),

   oldas("oldas", CommandType.Flag),

   oldcprops("oldcprops", CommandType.Destination),

   oldlinewrap("oldlinewrap", CommandType.Flag),

   oldpprops("oldpprops", CommandType.Destination),

   oldsprops("oldsprops", CommandType.Destination),

   oldtprops("oldtprops", CommandType.Destination),

   oleclsid("oleclsid", CommandType.Destination),

   operator("operator", CommandType.Destination),

   otblrul("otblrul", CommandType.Flag),

   outl("outl", CommandType.Toggle),

   outlinelevelN("outlinelevel", CommandType.Value),

   overlay("overlay", CommandType.Flag),

   page("page", CommandType.Symbol),

   pagebb("pagebb", CommandType.Flag),

   panose("panose", CommandType.Destination),

   paperh("paperh", CommandType.Value),

   paperw("paperw", CommandType.Value),

   par("par", CommandType.Symbol),

   pararsid("pararsid", CommandType.Value),

   pard("pard", CommandType.Flag),

   password("password", CommandType.Destination),

   passwordhash("passwordhash", CommandType.Destination),

   pc("pc", CommandType.Encoding),

   pca("pca", CommandType.Encoding),

   pgbrdrb("pgbrdrb", CommandType.Flag),

   pgbrdrfoot("pgbrdrfoot", CommandType.Flag),

   pgbrdrhead("pgbrdrhead", CommandType.Flag),

   pgbrdrl("pgbrdrl", CommandType.Flag),

   pgbrdropt("pgbrdropt", CommandType.Value),

   pgbrdrr("pgbrdrr", CommandType.Flag),

   pgbrdrsnap("pgbrdrsnap", CommandType.Flag),

   pgbrdrt("pgbrdrt", CommandType.Flag),

   pghsxn("pghsxn", CommandType.Value),

   pgnbidia("pgnbidia", CommandType.Flag),

   pgnbidib("pgnbidib", CommandType.Flag),

   pgnchosung("pgnchosung", CommandType.Flag),

   pgncnum("pgncnum", CommandType.Flag),

   pgncont("pgncont", CommandType.Flag),

   pgndbnum("pgndbnum", CommandType.Flag),

   pgndbnumd("pgndbnumd", CommandType.Flag),

   pgndbnumk("pgndbnumk", CommandType.Flag),

   pgndbnumt("pgndbnumt", CommandType.Flag),

   pgndec("pgndec", CommandType.Flag),

   pgndecd("pgndecd", CommandType.Flag),

   pgnganada("pgnganada", CommandType.Flag),

   pgngbnum("pgngbnum", CommandType.Flag),

   pgngbnumd("pgngbnumd", CommandType.Flag),

   pgngbnumk("pgngbnumk", CommandType.Flag),

   pgngbnuml("pgngbnuml", CommandType.Flag),

   pgnhindia("pgnhindia", CommandType.Flag),

   pgnhindib("pgnhindib", CommandType.Flag),

   pgnhindic("pgnhindic", CommandType.Flag),

   pgnhindid("pgnhindid", CommandType.Flag),

   pgnhnN("pgnhn", CommandType.Value),

   pgnhnsc("pgnhnsc", CommandType.Flag),

   pgnhnsh("pgnhnsh", CommandType.Flag),

   pgnhnsm("pgnhnsm", CommandType.Flag),

   pgnhnsn("pgnhnsn", CommandType.Flag),

   pgnhnsp("pgnhnsp", CommandType.Flag),

   pgnid("pgnid", CommandType.Flag),

   pgnlcltr("pgnlcltr", CommandType.Flag),

   pgnlcrm("pgnlcrm", CommandType.Flag),

   pgnrestart("pgnrestart", CommandType.Flag),

   pgnstart("pgnstart", CommandType.Value),

   pgnstarts("pgnstarts", CommandType.Value),

   pgnthaia("pgnthaia", CommandType.Flag),

   pgnthaib("pgnthaib", CommandType.Flag),

   pgnthaic("pgnthaic", CommandType.Flag),

   pgnucltr("pgnucltr", CommandType.Flag),

   pgnucrm("pgnucrm", CommandType.Flag),

   pgnvieta("pgnvieta", CommandType.Flag),

   pgnx("pgnx", CommandType.Value),

   pgny("pgny", CommandType.Value),

   pgnzodiac("pgnzodiac", CommandType.Flag),

   pgnzodiacd("pgnzodiacd", CommandType.Flag),

   pgnzodiacl("pgnzodiacl", CommandType.Flag),

   pgp("pgp", CommandType.Destination),

   pgptbl("pgptbl", CommandType.Destination),

   pgwsxn("pgwsxn", CommandType.Value),

   phcol("phcol", CommandType.Flag),

   phmrg("phmrg", CommandType.Flag),

   phpg("phpg", CommandType.Flag),

   picbmp("picbmp", CommandType.Flag),

   picbppN("picbpp", CommandType.Value),

   piccropb("piccropb", CommandType.Value),

   piccropl("piccropl", CommandType.Value),

   piccropr("piccropr", CommandType.Value),

   piccropt("piccropt", CommandType.Value),

   pich("pich", CommandType.Value),

   pichgoal("pichgoal", CommandType.Value),

   picprop("picprop", CommandType.Destination),

   picscaled("picscaled", CommandType.Flag),

   picscalex("picscalex", CommandType.Value),

   picscaley("picscaley", CommandType.Value),

   pict("pict", CommandType.Destination),

   picw("picw", CommandType.Value),

   picwgoal("picwgoal", CommandType.Value),

   pindtabqc("pindtabqc", CommandType.Flag),

   pindtabql("pindtabql", CommandType.Flag),

   pindtabqr("pindtabqr", CommandType.Flag),

   plain("plain", CommandType.Flag),

   pmartabqc("pmartabqc", CommandType.Flag),

   pmartabql("pmartabql", CommandType.Flag),

   pmartabqr("pmartabqr", CommandType.Flag),

   pmmetafile("pmmetafile", CommandType.Value),

   pn("pn", CommandType.Destination),

   pnacross("pnacross", CommandType.Flag),

   pnaiu("pnaiu", CommandType.Flag),

   pnaiud("pnaiud", CommandType.Flag),

   pnaiueo("pnaiueo", CommandType.Flag),

   pnaiueod("pnaiueod", CommandType.Flag),

   pnb("pnb", CommandType.Toggle),

   pnbidia("pnbidia", CommandType.Flag),

   pnbidib("pnbidib", CommandType.Flag),

   pncaps("pncaps", CommandType.Toggle),

   pncard("pncard", CommandType.Flag),

   pncfN("pncf", CommandType.Value),

   pnchosung("pnchosung", CommandType.Flag),

   pncnum("pncnum", CommandType.Flag),

   pndbnum("pndbnum", CommandType.Flag),

   pndbnumd("pndbnumd", CommandType.Flag),

   pndbnumk("pndbnumk", CommandType.Flag),

   pndbnuml("pndbnuml", CommandType.Flag),

   pndbnumt("pndbnumt", CommandType.Flag),

   pndec("pndec", CommandType.Flag),

   pndecd("pndecd", CommandType.Flag),

   pnfN("pnf", CommandType.Value),

   pnfsN("pnfs", CommandType.Value),

   pnganada("pnganada", CommandType.Flag),

   pngblip("pngblip", CommandType.Flag),

   pngbnum("pngbnum", CommandType.Flag),

   pngbnumd("pngbnumd", CommandType.Flag),

   pngbnumk("pngbnumk", CommandType.Flag),

   pngbnuml("pngbnuml", CommandType.Flag),

   pnhang("pnhang", CommandType.Flag),

   pni("pni", CommandType.Toggle),

   pnindentN("pnindent", CommandType.Value),

   pniroha("pniroha", CommandType.Flag),

   pnirohad("pnirohad", CommandType.Flag),

   pnlcltr("pnlcltr", CommandType.Flag),

   pnlcrm("pnlcrm", CommandType.Flag),

   pnlvlN("pnlvl", CommandType.Value),

   pnlvlblt("pnlvlblt", CommandType.Flag),

   pnlvlbody("pnlvlbody", CommandType.Flag),

   pnlvlcont("pnlvlcont", CommandType.Flag),

   pnnumonce("pnnumonce", CommandType.Flag),

   pnord("pnord", CommandType.Flag),

   pnordt("pnordt", CommandType.Flag),

   pnprev("pnprev", CommandType.Flag),

   pnqc("pnqc", CommandType.Flag),

   pnql("pnql", CommandType.Flag),

   pnqr("pnqr", CommandType.Flag),

   pnrauth("pnrauth", CommandType.Value),

   pnrdate("pnrdate", CommandType.Value),

   pnrestart("pnrestart", CommandType.Flag),

   pnrnfc("pnrnfc", CommandType.Value),

   pnrnot("pnrnot", CommandType.Flag),

   pnrpnbr("pnrpnbr", CommandType.Value),

   pnrrgb("pnrrgb", CommandType.Value),

   pnrstart("pnrstart", CommandType.Value),

   pnrstop("pnrstop", CommandType.Value),

   pnrxst("pnrxst", CommandType.Value),

   pnscaps("pnscaps", CommandType.Toggle),

   pnseclvl("pnseclvl", CommandType.Destination), // and Value

   pnspN("pnsp", CommandType.Value),

   pnstartN("pnstart", CommandType.Value),

   pnstrike("pnstrike", CommandType.Toggle),

   pntext("pntext", CommandType.Destination),

   pntxta("pntxta", CommandType.Destination),

   pntxtb("pntxtb", CommandType.Destination),

   pnucltr("pnucltr", CommandType.Flag),

   pnucrm("pnucrm", CommandType.Flag),

   pnul("pnul", CommandType.Toggle),

   pnuld("pnuld", CommandType.Flag),

   pnuldash("pnuldash", CommandType.Flag),

   pnuldashd("pnuldashd", CommandType.Flag),

   pnuldashdd("pnuldashdd", CommandType.Flag),

   pnuldb("pnuldb", CommandType.Flag),

   pnulhair("pnulhair", CommandType.Flag),

   pnulnone("pnulnone", CommandType.Flag),

   pnulth("pnulth", CommandType.Flag),

   pnulw("pnulw", CommandType.Flag),

   pnulwave("pnulwave", CommandType.Flag),

   pnzodiac("pnzodiac", CommandType.Flag),

   pnzodiacd("pnzodiacd", CommandType.Flag),

   pnzodiacl("pnzodiacl", CommandType.Flag),

   posnegxN("posnegx", CommandType.Value),

   posnegyN("posnegy", CommandType.Value),

   posx("posx", CommandType.Value),

   posxc("posxc", CommandType.Flag),

   posxi("posxi", CommandType.Flag),

   posxl("posxl", CommandType.Flag),

   posxo("posxo", CommandType.Flag),

   posxr("posxr", CommandType.Flag),

   posy("posy", CommandType.Value),

   posyb("posyb", CommandType.Flag),

   posyc("posyc", CommandType.Flag),

   posyil("posyil", CommandType.Flag),

   posyin("posyin", CommandType.Flag),

   posyout("posyout", CommandType.Flag),

   posyt("posyt", CommandType.Flag),

   prauth("prauth", CommandType.Value),

   prcolbl("prcolbl", CommandType.Flag),

   prdate("prdate", CommandType.Value),

   printdata("printdata", CommandType.Flag),

   printim("printim", CommandType.Destination),

   privatecmd("private", CommandType.Destination),

   propname("propname", CommandType.Destination),

   proptype("proptype", CommandType.Value),

   protect("protect", CommandType.Toggle),

   protend("protend", CommandType.Destination),

   protlevel("protlevel", CommandType.Value),

   protstart("protstart", CommandType.Destination),

   protusertbl("protusertbl", CommandType.Destination),

   psover("psover", CommandType.Flag),

   pszN("psz", CommandType.Value),

   ptabldot("ptabldot", CommandType.Flag),

   ptablmdot("ptablmdot", CommandType.Flag),

   ptablminus("ptablminus", CommandType.Flag),

   ptablnone("ptablnone", CommandType.Flag),

   ptabluscore("ptabluscore", CommandType.Flag),

   pubauto("pubauto", CommandType.Flag),

   pvmrg("pvmrg", CommandType.Flag),

   pvpara("pvpara", CommandType.Flag),

   pvpg("pvpg", CommandType.Flag),

   pwd("pwd", CommandType.Value),

   pxe("pxe", CommandType.Destination),

   qc("qc", CommandType.Flag),

   qd("qd", CommandType.Flag),

   qj("qj", CommandType.Flag),

   qk("qk", CommandType.Value),

   ql("ql", CommandType.Flag),

   qmspace("qmspace", CommandType.Symbol),

   qr("qr", CommandType.Flag),

   qt("qt", CommandType.Flag),

   rawclbgdkbdiag("rawclbgdkbdiag", CommandType.Flag),

   rawclbgbdiag("rawclbgbdiag", CommandType.Flag),

   rawclbgcross("rawclbgcross", CommandType.Flag),

   rawclbgdcross("rawclbgdcross", CommandType.Flag),

   rawclbgdkcross("rawclbgdkcross", CommandType.Flag),

   rawclbgdkdcross("rawclbgdkdcross", CommandType.Flag),

   rawclbgdkfdiag("rawclbgdkfdiag", CommandType.Flag),

   rawclbgdkhor("rawclbgdkhor", CommandType.Flag),

   rawclbgdkvert("rawclbgdkvert", CommandType.Flag),

   rawclbgfdiag("rawclbgfdiag", CommandType.Flag),

   rawclbghoriz("rawclbghoriz", CommandType.Flag),

   rawclbgvert("rawclbgvert", CommandType.Flag),

   rdblquote("rdblquote", CommandType.Symbol),

   readonlyrecommended("readonlyrecommended", CommandType.Flag),

   readprot("readprot", CommandType.Flag),

   red("red", CommandType.Value),

   relyonvml("relyonvml", CommandType.Value),

   remdttm("remdttm", CommandType.Flag),

   rempersonalinfo("rempersonalinfo", CommandType.Flag),

   result("result", CommandType.Destination),

   revauthN("revauth", CommandType.Value),

   revauthdel("revauthdel", CommandType.Value),

   revbar("revbar", CommandType.Value),

   revdttmN("revdttm", CommandType.Value),

   revdttmdel("revdttmdel", CommandType.Value),

   revised("revised", CommandType.Toggle),

   revisions("revisions", CommandType.Flag),

   revprop("revprop", CommandType.Value),

   revprot("revprot", CommandType.Flag),

   revtbl("revtbl", CommandType.Destination),

   revtim("revtim", CommandType.Destination),

   ri("ri", CommandType.Value),

   rin("rin", CommandType.Value),

   row("row", CommandType.Symbol),

   rquote("rquote", CommandType.Symbol),

   rsid("rsid", CommandType.Value),

   rsidroot("rsidroot", CommandType.Value),

   rsidtbl("rsidtbl", CommandType.Destination),

   rsltbmp("rsltbmp", CommandType.Flag),

   rslthtml("rslthtml", CommandType.Flag),

   rsltmerge("rsltmerge", CommandType.Flag),

   rsltpict("rsltpict", CommandType.Flag),

   rsltrtf("rsltrtf", CommandType.Flag),

   rslttxt("rslttxt", CommandType.Flag),

   rtf("rtf", CommandType.Destination),

   rtlch("rtlch", CommandType.Flag),

   rtldoc("rtldoc", CommandType.Flag),

   rtlgutter("rtlgutter", CommandType.Flag),

   rtlmark("rtlmark", CommandType.Symbol),

   rtlpar("rtlpar", CommandType.Flag),

   rtlrow("rtlrow", CommandType.Flag),

   rtlsect("rtlsect", CommandType.Flag),

   rxe("rxe", CommandType.Destination),

   s("s", CommandType.Value),

   sa("sa", CommandType.Value),

   saauto("saauto", CommandType.Toggle),

   saftnnalc("saftnnalc", CommandType.Flag),

   saftnnar("saftnnar", CommandType.Flag),

   saftnnauc("saftnnauc", CommandType.Flag),

   saftnnchi("saftnnchi", CommandType.Flag),

   saftnnchosung("saftnnchosung", CommandType.Flag),

   saftnncnum("saftnncnum", CommandType.Flag),

   saftnndbar("saftnndbar", CommandType.Flag),

   saftnndbnum("saftnndbnum", CommandType.Flag),

   saftnndbnumd("saftnndbnumd", CommandType.Flag),

   saftnndbnumk("saftnndbnumk", CommandType.Flag),

   saftnndbnumt("saftnndbnumt", CommandType.Flag),

   saftnnganada("saftnnganada", CommandType.Flag),

   saftnngbnum("saftnngbnum", CommandType.Flag),

   saftnngbnumd("saftnngbnumd", CommandType.Flag),

   saftnngbnumk("saftnngbnumk", CommandType.Flag),

   saftnngbnuml("saftnngbnuml", CommandType.Flag),

   saftnnrlc("saftnnrlc", CommandType.Flag),

   saftnnruc("saftnnruc", CommandType.Flag),

   saftnnzodiac("saftnnzodiac", CommandType.Flag),

   saftnnzodiacd("saftnnzodiacd", CommandType.Flag),

   saftnnzodiacl("saftnnzodiacl", CommandType.Flag),

   saftnrestart("saftnrestart", CommandType.Flag),

   saftnrstcont("saftnrstcont", CommandType.Flag),

   saftnstart("saftnstart", CommandType.Value),

   sautoupd("sautoupd", CommandType.Flag),

   saveinvalidxml("saveinvalidxml", CommandType.Flag),

   saveprevpict("saveprevpict", CommandType.Flag),

   sb("sb", CommandType.Value),

   sbasedon("sbasedon", CommandType.Value),

   sbauto("sbauto", CommandType.Toggle),

   sbkcol("sbkcol", CommandType.Flag),

   sbkeven("sbkeven", CommandType.Flag),

   sbknone("sbknone", CommandType.Flag),

   sbkodd("sbkodd", CommandType.Flag),

   sbkpage("sbkpage", CommandType.Flag),

   sbys("sbys", CommandType.Flag),

   scaps("scaps", CommandType.Toggle),

   scompose("scompose", CommandType.Flag),

   sec("sec", CommandType.Value),

   sect("sect", CommandType.Symbol),

   sectd("sectd", CommandType.Flag),

   sectdefaultcl("sectdefaultcl", CommandType.Flag),

   sectexpand("sectexpand", CommandType.Value),

   sectlinegrid("sectlinegrid", CommandType.Value),

   sectnum("sectnum", CommandType.Symbol),

   sectrsid("sectrsid", CommandType.Value),

   sectspecifycl("sectspecifycl", CommandType.Flag),

   sectspecifygen("sectspecifygen", CommandType.Flag),

   sectspecifyl("sectspecifyl", CommandType.Flag),

   sectunlocked("sectunlocked", CommandType.Flag),

   sftnbj("sftnbj", CommandType.Flag),

   sftnnalc("sftnnalc", CommandType.Flag),

   sftnnar("sftnnar", CommandType.Flag),

   sftnnauc("sftnnauc", CommandType.Flag),

   sftnnchi("sftnnchi", CommandType.Flag),

   sftnnchosung("sftnnchosung", CommandType.Flag),

   sftnncnum("sftnncnum", CommandType.Flag),

   sftnndbar("sftnndbar", CommandType.Flag),

   sftnndbnum("sftnndbnum", CommandType.Flag),

   sftnndbnumd("sftnndbnumd", CommandType.Flag),

   sftnndbnumk("sftnndbnumk", CommandType.Flag),

   sftnndbnumt("sftnndbnumt", CommandType.Flag),

   sftnnganada("sftnnganada", CommandType.Flag),

   sftnngbnum("sftnngbnum", CommandType.Flag),

   sftnngbnumd("sftnngbnumd", CommandType.Flag),

   sftnngbnumk("sftnngbnumk", CommandType.Flag),

   sftnngbnuml("sftnngbnuml", CommandType.Flag),

   sftnnrlc("sftnnrlc", CommandType.Flag),

   sftnnruc("sftnnruc", CommandType.Flag),

   sftnnzodiac("sftnnzodiac", CommandType.Flag),

   sftnnzodiacd("sftnnzodiacd", CommandType.Flag),

   sftnnzodiacl("sftnnzodiacl", CommandType.Flag),

   sftnrestart("sftnrestart", CommandType.Flag),

   sftnrstcont("sftnrstcont", CommandType.Flag),

   sftnrstpg("sftnrstpg", CommandType.Flag),

   sftnstart("sftnstart", CommandType.Value),

   sftntj("sftntj", CommandType.Flag),

   shad("shad", CommandType.Toggle),

   shading("shading", CommandType.Value),

   shidden("shidden", CommandType.Flag),

   shift("shift", CommandType.Flag),

   showplaceholdtext("showplaceholdtext", CommandType.Value),

   showxmlerrors("showxmlerrors", CommandType.Value),

   shp("shp", CommandType.Destination),

   shpbottom("shpbottom", CommandType.Value),

   shpbxcolumn("shpbxcolumn", CommandType.Flag),

   shpbxignore("shpbxignore", CommandType.Flag),

   shpbxmargin("shpbxmargin", CommandType.Flag),

   shpbxpage("shpbxpage", CommandType.Flag),

   shpbyignore("shpbyignore", CommandType.Flag),

   shpbymargin("shpbymargin", CommandType.Flag),

   shpbypage("shpbypage", CommandType.Flag),

   shpbypara("shpbypara", CommandType.Flag),

   shpfblwtxt("shpfblwtxt", CommandType.Value),

   shpfhdr("shpfhdr", CommandType.Value),

   shpgrp("shpgrp", CommandType.Destination),

   shpinst("shpinst", CommandType.Destination),

   shpleft("shpleft", CommandType.Value),

   shplid("shplid", CommandType.Value),

   shplockanchor("shplockanchor", CommandType.Flag),

   shppict("shppict", CommandType.Destination),

   shpright("shpright", CommandType.Value),

   shprslt("shprslt", CommandType.Destination),

   shptop("shptop", CommandType.Value),

   shptxt("shptxt", CommandType.Destination),

   shpwrk("shpwrk", CommandType.Value),

   shpwr("shpwr", CommandType.Value),

   shpz("shpz", CommandType.Value),

   sl("sl", CommandType.Value),

   slink("slink", CommandType.Value),

   slmult("slmult", CommandType.Value),

   slocked("slocked", CommandType.Flag),

   sn("sn", CommandType.Destination),

   snaptogridincell("snaptogridincell", CommandType.Flag),

   snext("snext", CommandType.Value),

   softcol("softcol", CommandType.Flag),

   softlheightN("softlheight", CommandType.Value),

   softline("softline", CommandType.Flag),

   softpage("softpage", CommandType.Flag),

   sp("sp", CommandType.Destination),

   spersonal("spersonal", CommandType.Flag),

   spltpgpar("spltpgpar", CommandType.Flag),

   splytwnine("splytwnine", CommandType.Flag),

   spriority("spriority", CommandType.Value),

   sprsbsp("sprsbsp", CommandType.Flag),

   sprslnsp("sprslnsp", CommandType.Flag),

   sprsspbf("sprsspbf", CommandType.Flag),

   sprstsm("sprstsm", CommandType.Flag),

   sprstsp("sprstsp", CommandType.Flag),

   spv("spv", CommandType.Flag),

   sqformat("sqformat", CommandType.Flag),

   srauth("srauth", CommandType.Value),

   srdate("srdate", CommandType.Value),

   sreply("sreply", CommandType.Flag),

   ssemihidden("ssemihidden", CommandType.Value),

   staticval("staticval", CommandType.Destination),

   stextflow("stextflow", CommandType.Value),

   strike("strike", CommandType.Toggle),

   striked("striked", CommandType.Toggle),

   stshfbi("stshfbi", CommandType.Value),

   stshfdbch("stshfdbch", CommandType.Value),

   stshfhich("stshfhich", CommandType.Value),

   stshfloch("stshfloch", CommandType.Value),

   stylelock("stylelock", CommandType.Flag),

   stylelockbackcomp("stylelockbackcomp", CommandType.Flag),

   stylelockenforced("stylelockenforced", CommandType.Flag),

   stylelockqfset("stylelockqfset", CommandType.Flag),

   stylelocktheme("stylelocktheme", CommandType.Flag),

   stylesheet("stylesheet", CommandType.Destination),

   stylesortmethod("stylesortmethod", CommandType.Value),

   styrsid("styrsid", CommandType.Value),

   sub("sub", CommandType.Flag),

   subdocumentN("subdocument", CommandType.Value),

   subfontbysize("subfontbysize", CommandType.Flag),

   subject("subject", CommandType.Destination),

   sunhideused("sunhideused", CommandType.Value),

   supercmd("super", CommandType.Flag),

   sv("sv", CommandType.Destination),

   svb("svb", CommandType.Destination),

   swpbdr("swpbdr", CommandType.Flag),

   tab("tab", CommandType.Symbol),

   tabsnoovrlp("tabsnoovrlp", CommandType.Flag),

   taprtl("taprtl", CommandType.Flag),

   tb("tb", CommandType.Value),

   tblind("tblind", CommandType.Value),

   tblindtype("tblindtype", CommandType.Value),

   tbllkbestfit("tbllkbestfit", CommandType.Flag),

   tbllkborder("tbllkborder", CommandType.Flag),

   tbllkcolor("tbllkcolor", CommandType.Flag),

   tbllkfont("tbllkfont", CommandType.Flag),

   tbllkhdrcols("tbllkhdrcols", CommandType.Flag),

   tbllkhdrrows("tbllkhdrrows", CommandType.Flag),

   tbllklastcol("tbllklastcol", CommandType.Flag),

   tbllklastrow("tbllklastrow", CommandType.Flag),

   tbllknocolband("tbllknocolband", CommandType.Flag),

   tbllknorowband("tbllknorowband", CommandType.Flag),

   tbllkshading("tbllkshading", CommandType.Flag),

   tblrsid("tblrsid", CommandType.Value),

   tc("tc", CommandType.Destination),

   tcelld("tcelld", CommandType.Flag),

   tcf("tcf", CommandType.Value),

   tcl("tcl", CommandType.Value),

   tcn("tcn", CommandType.Flag),

   tdfrmtxtBottom("tdfrmtxtBottom", CommandType.Value),

   tdfrmtxtLeft("tdfrmtxtLeft", CommandType.Value),

   tdfrmtxtRight("tdfrmtxtRight", CommandType.Value),

   tdfrmtxtTop("tdfrmtxtTop", CommandType.Value),

   template("template", CommandType.Destination),

   themedata("themedata", CommandType.Destination),

   themelang("themelang", CommandType.Value),

   themelangcs("themelangcs", CommandType.Value),

   themelangfe("themelangfe", CommandType.Value),

   time("time", CommandType.Flag),

   title("title", CommandType.Destination),

   titlepg("titlepg", CommandType.Flag),

   tldot("tldot", CommandType.Flag),

   tleq("tleq", CommandType.Flag),

   tlhyph("tlhyph", CommandType.Flag),

   tlmdot("tlmdot", CommandType.Flag),

   tlth("tlth", CommandType.Flag),

   tlul("tlul", CommandType.Flag),

   toplinepunct("toplinepunct", CommandType.Flag),

   tphcol("tphcol", CommandType.Flag),

   tphmrg("tphmrg", CommandType.Flag),

   tphpg("tphpg", CommandType.Flag),

   tposnegx("tposnegx", CommandType.Value),

   tposnegy("tposnegy", CommandType.Value),

   tposxc("tposxc", CommandType.Flag),

   tposxi("tposxi", CommandType.Flag),

   tposxl("tposxl", CommandType.Flag),

   tposx("tposx", CommandType.Value),

   tposxo("tposxo", CommandType.Flag),

   tposxr("tposxr", CommandType.Flag),

   tposy("tposy", CommandType.Value),

   tposyb("tposyb", CommandType.Flag),

   tposyc("tposyc", CommandType.Flag),

   tposyil("tposyil", CommandType.Flag),

   tposyin("tposyin", CommandType.Flag),

   tposyout("tposyout", CommandType.Flag),

   tposyt("tposyt", CommandType.Flag),

   tpvmrg("tpvmrg", CommandType.Flag),

   tpvpara("tpvpara", CommandType.Flag),

   tpvpg("tpvpg", CommandType.Flag),

   tqc("tqc", CommandType.Flag),

   tqdec("tqdec", CommandType.Flag),

   tqr("tqr", CommandType.Flag),

   trackformatting("trackformatting", CommandType.Value),

   trackmoves("trackmoves", CommandType.Value),

   transmf("transmf", CommandType.Flag),

   trauth("trauth", CommandType.Value),

   trautofit("trautofit", CommandType.Toggle),

   trbgbdiag("trbgbdiag", CommandType.Flag),

   trbgcross("trbgcross", CommandType.Flag),

   trbgdcross("trbgdcross", CommandType.Flag),

   trbgdkbdiag("trbgdkbdiag", CommandType.Flag),

   trbgdkcross("trbgdkcross", CommandType.Flag),

   trbgdkdcross("trbgdkdcross", CommandType.Flag),

   trbgdkfdiag("trbgdkfdiag", CommandType.Flag),

   trbgdkhor("trbgdkhor", CommandType.Flag),

   trbgdkvert("trbgdkvert", CommandType.Flag),

   trbgfdiag("trbgfdiag", CommandType.Flag),

   trbghoriz("trbghoriz", CommandType.Flag),

   trbgvert("trbgvert", CommandType.Flag),

   trbrdrb("trbrdrb", CommandType.Flag),

   trbrdrh("trbrdrh", CommandType.Flag),

   trbrdrl("trbrdrl", CommandType.Flag),

   trbrdrr("trbrdrr", CommandType.Flag),

   trbrdrt("trbrdrt", CommandType.Flag),

   trbrdrv("trbrdrv", CommandType.Flag),

   trcbpat("trcbpat", CommandType.Value),

   trcfpat("trcfpat", CommandType.Value),

   trdate("trdate", CommandType.Value),

   trftsWidthA("trftsWidthA", CommandType.Value),

   trftsWidthB("trftsWidthB", CommandType.Value),

   trftsWidth("trftsWidth", CommandType.Value),

   trgaph("trgaph", CommandType.Value),

   trhdr("trhdr", CommandType.Flag),

   trkeep("trkeep", CommandType.Flag),

   trkeepfollow("trkeepfollow", CommandType.Flag),

   trleft("trleft", CommandType.Value),

   trowd("trowd", CommandType.Flag),

   trpaddb("trpaddb", CommandType.Value),

   trpaddfb("trpaddfb", CommandType.Value),

   trpaddfl("trpaddfl", CommandType.Value),

   trpaddfr("trpaddfr", CommandType.Value),

   trpaddft("trpaddft", CommandType.Value),

   trpaddl("trpaddl", CommandType.Value),

   trpaddr("trpaddr", CommandType.Value),

   trpaddt("trpaddt", CommandType.Value),

   trpadob("trpadob", CommandType.Value),

   trpadofb("trpadofb", CommandType.Value),

   trpadofl("trpadofl", CommandType.Value),

   trpadofr("trpadofr", CommandType.Value),

   trpadoft("trpadoft", CommandType.Value),

   trpadol("trpadol", CommandType.Value),

   trpador("trpador", CommandType.Value),

   trpadot("trpadot", CommandType.Value),

   trpat("trpat", CommandType.Value),

   trqc("trqc", CommandType.Flag),

   trql("trql", CommandType.Flag),

   trqr("trqr", CommandType.Flag),

   trrh("trrh", CommandType.Value),

   trshdng("trshdng", CommandType.Value),

   trspdb("trspdb", CommandType.Value),

   trspdfb("trspdfb", CommandType.Value),

   trspdfl("trspdfl", CommandType.Value),

   trspdfr("trspdfr", CommandType.Value),

   trspdft("trspdft", CommandType.Value),

   trspdl("trspdl", CommandType.Value),

   trspdr("trspdr", CommandType.Value),

   trspdt("trspdt", CommandType.Value),

   trspob("trspob", CommandType.Value),

   trspofb("trspofb", CommandType.Value),

   trspofl("trspofl", CommandType.Value),

   trspofr("trspofr", CommandType.Value),

   trspoft("trspoft", CommandType.Value),

   trspol("trspol", CommandType.Value),

   trspor("trspor", CommandType.Value),

   trspot("trspot", CommandType.Value),

   truncatefontheight("truncatefontheight", CommandType.Flag),

   truncex("truncex", CommandType.Flag),

   trwWidthA("trwWidthA", CommandType.Value),

   trwWidthB("trwWidthB", CommandType.Value),

   trwWidth("trwWidth", CommandType.Value),

   ts("ts", CommandType.Value),

   tsbgbdiag("tsbgbdiag", CommandType.Flag),

   tsbgcross("tsbgcross", CommandType.Flag),

   tsbgdcross("tsbgdcross", CommandType.Flag),

   tsbgdkbdiag("tsbgdkbdiag", CommandType.Flag),

   tsbgdkcross("tsbgdkcross", CommandType.Flag),

   tsbgdkdcross("tsbgdkdcross", CommandType.Flag),

   tsbgdkfdiag("tsbgdkfdiag", CommandType.Flag),

   tsbgdkhor("tsbgdkhor", CommandType.Flag),

   tsbgdkvert("tsbgdkvert", CommandType.Flag),

   tsbgfdiag("tsbgfdiag", CommandType.Flag),

   tsbghoriz("tsbghoriz", CommandType.Flag),

   tsbgvert("tsbgvert", CommandType.Flag),

   tsbrdrb("tsbrdrb", CommandType.Flag),

   tsbrdrdgl("tsbrdrdgl", CommandType.Flag),

   tsbrdrdgr("tsbrdrdgr", CommandType.Flag),

   tsbrdrh("tsbrdrh", CommandType.Flag),

   tsbrdrl("tsbrdrl", CommandType.Flag),

   tsbrdrr("tsbrdrr", CommandType.Flag),

   tsbrdrt("tsbrdrt", CommandType.Flag),

   tsbrdrv("tsbrdrv", CommandType.Flag),

   tscbandhorzeven("tscbandhorzeven", CommandType.Flag),

   tscbandhorzodd("tscbandhorzodd", CommandType.Flag),

   tscbandsh("tscbandsh", CommandType.Value),

   tscbandsv("tscbandsv", CommandType.Value),

   tscbandverteven("tscbandverteven", CommandType.Flag),

   tscbandvertodd("tscbandvertodd", CommandType.Flag),

   tscellcbpat("tscellcbpat", CommandType.Value),

   tscellcfpat("tscellcfpat", CommandType.Value),

   tscellpaddb("tscellpaddb", CommandType.Value),

   tscellpaddfb("tscellpaddfb", CommandType.Value),

   tscellpaddfl("tscellpaddfl", CommandType.Value),

   tscellpaddfr("tscellpaddfr", CommandType.Value),

   tscellpaddft("tscellpaddft", CommandType.Value),

   tscellpaddl("tscellpaddl", CommandType.Value),

   tscellpaddr("tscellpaddr", CommandType.Value),

   tscellpaddt("tscellpaddt", CommandType.Value),

   tscellpct("tscellpct", CommandType.Value),

   tscellwidth("tscellwidth", CommandType.Value),

   tscellwidthfts("tscellwidthfts", CommandType.Value),

   tscfirstcol("tscfirstcol", CommandType.Flag),

   tscfirstrow("tscfirstrow", CommandType.Flag),

   tsclastcol("tsclastcol", CommandType.Flag),

   tsclastrow("tsclastrow", CommandType.Flag),

   tscnecell("tscnecell", CommandType.Flag),

   tscnwcell("tscnwcell", CommandType.Flag),

   tscsecell("tscsecell", CommandType.Flag),

   tscswcell("tscswcell", CommandType.Flag),

   tsd("tsd", CommandType.Flag),

   tsnowrap("tsnowrap", CommandType.Flag),

   tsrowd("tsrowd", CommandType.Flag),

   tsvertalb("tsvertalb", CommandType.Flag),

   tsvertalc("tsvertalc", CommandType.Flag),

   tsvertalt("tsvertalt", CommandType.Flag),

   twoinone("twoinone", CommandType.Value),

   twoonone("twoonone", CommandType.Flag),

   tx("tx", CommandType.Value),

   txbxtwalways("txbxtwalways", CommandType.Flag),

   txbxtwfirst("txbxtwfirst", CommandType.Flag),

   txbxtwfirstlast("txbxtwfirstlast", CommandType.Flag),

   txbxtwlast("txbxtwlast", CommandType.Flag),

   txbxtwno("txbxtwno", CommandType.Flag),

   txe("txe", CommandType.Destination),

   u("u", CommandType.Value),

   uc("uc", CommandType.Value),

   ud("ud", CommandType.Destination),

   ul("ul", CommandType.Toggle),

   ulc("ulc", CommandType.Value),

   uld("uld", CommandType.Flag),

   uldash("uldash", CommandType.Toggle),

   uldashd("uldashd", CommandType.Toggle),

   uldashdd("uldashdd", CommandType.Toggle),

   uldb("uldb", CommandType.Toggle),

   ulhair("ulhair", CommandType.Toggle),

   ulhwave("ulhwave", CommandType.Toggle),

   ulldash("ulldash", CommandType.Toggle),

   ulnone("ulnone", CommandType.Flag),

   ulth("ulth", CommandType.Toggle),

   ulthd("ulthd", CommandType.Toggle),

   ulthdash("ulthdash", CommandType.Toggle),

   ulthdashd("ulthdashd", CommandType.Toggle),

   ulthdashdd("ulthdashdd", CommandType.Toggle),

   ulthldash("ulthldash", CommandType.Toggle),

   ululdbwave("ululdbwave", CommandType.Toggle),

   ulw("ulw", CommandType.Flag),

   ulwave("ulwave", CommandType.Toggle),

   up("up", CommandType.Value),

   upr("upr", CommandType.Destination),

   urtf("urtf", CommandType.Value),

   useltbaln("useltbaln", CommandType.Flag),

   usenormstyforlist("usenormstyforlist", CommandType.Flag),

   userprops("userprops", CommandType.Destination),

   usexform("usexform", CommandType.Flag),

   utinl("utinl", CommandType.Flag),

   v("v", CommandType.Toggle),

   validatexml("validatexml", CommandType.Value),

   vern("vern", CommandType.Value),

   version("version", CommandType.Value),

   vertal("vertal", CommandType.Flag),

   vertalb("vertalb", CommandType.Flag),

   vertalc("vertalc", CommandType.Flag),

   vertalj("vertalj", CommandType.Flag),

   vertalt("vertalt", CommandType.Flag),

   vertdoc("vertdoc", CommandType.Flag),

   vertsect("vertsect", CommandType.Flag),

   viewbksp("viewbksp", CommandType.Value),

   viewkind("viewkind", CommandType.Value),

   viewnobound("viewnobound", CommandType.Flag),

   viewscale("viewscale", CommandType.Value),

   viewzk("viewzk", CommandType.Value),

   wbitmap("wbitmap", CommandType.Value),

   wbmbitspixel("wbmbitspixel", CommandType.Value),

   wbmplanes("wbmplanes", CommandType.Value),

   wbmwidthbyte("wbmwidthbyte", CommandType.Value),

   webhidden("webhidden", CommandType.Flag),

   wgrffmtfilter("wgrffmtfilter", CommandType.Destination),

   widctlpar("widctlpar", CommandType.Flag),

   widowctrl("widowctrl", CommandType.Flag),

   windowcaption("windowcaption", CommandType.Destination),

   wmetafile("wmetafile", CommandType.Value),

   wpeqn("wpeqn", CommandType.Flag),

   wpjst("wpjst", CommandType.Flag),

   wpsp("wpsp", CommandType.Flag),

   wraparound("wraparound", CommandType.Flag),

   wrapdefault("wrapdefault", CommandType.Flag),

   wrapthrough("wrapthrough", CommandType.Flag),

   wraptight("wraptight", CommandType.Flag),

   wraptrsp("wraptrsp", CommandType.Flag),

   writereservation("writereservation", CommandType.Destination),

   writereservhash("writereservhash", CommandType.Destination),

   wrppunct("wrppunct", CommandType.Flag),

   xe("xe", CommandType.Destination),

   xef("xef", CommandType.Value),

   xform("xform", CommandType.Destination),

   xmlattr("xmlattr", CommandType.Flag),

   xmlattrname("xmlattrname", CommandType.Destination),

   xmlattrns("xmlattrns", CommandType.Value),

   xmlattrvalue("xmlattrvalue", CommandType.Destination),

   xmlclose("xmlclose", CommandType.Destination),

   xmlname("xmlname", CommandType.Destination),

   xmlns("xmlns", CommandType.Value),

   xmlnstbl("xmlnstbl", CommandType.Destination),

   xmlopen("xmlopen", CommandType.Destination),

   xmlsdttcell("xmlsdttcell", CommandType.Flag),

   xmlsdttpara("xmlsdttpara", CommandType.Flag),

   xmlsdttregular("xmlsdttregular", CommandType.Flag),

   xmlsdttrow("xmlsdttrow", CommandType.Flag),

   xmlsdttunknown("xmlsdttunknown", CommandType.Flag),

   yr("yr", CommandType.Value),

   yts("yts", CommandType.Value),

   yxe("yxe", CommandType.Flag),

   zwbo("zwbo", CommandType.Symbol),

   zwj("zwj", CommandType.Symbol),

   zwnbo("zwnbo", CommandType.Symbol),

   zwnj("zwnj", CommandType.Symbol),

   outdisponlyhtml("outdisponlyhtml", CommandType.Toggle), // Not in the spec

   cocoartf("cocoartf", CommandType.Value), // Not in the spec

   cocoasubrtf("cocoasubrtf", CommandType.Value); // Not in the spec

   /**
    * Constructor.
    */
   private Command(String commandName, CommandType commandType)
   {
      this.commandName = commandName;
      this.commandType = commandType;
   }

   /**
    * Retrieve the command name, as it appears in the RTF file.
    */
   public String getCommandName()
   {
      return commandName;
   }

   /**
    * Retrieve the command type.
    */
   public CommandType getCommandType()
   {
      return commandType;
   }

   /**
    * Translate a name into a Command instance.
    */
   public static Command getInstance(String commandName)
   {
      return MAP.get(commandName);
   }

   private final String commandName;
   private final CommandType commandType;

   private static final Map<String, Command> MAP = new HashMap<String, Command>();
   static
   {
      for (Command command : Command.values())
      {
         MAP.put(command.getCommandName(), command);
      }
      MAP.put("\r", Command.par);
      MAP.put("\n", Command.par);
   }
}
