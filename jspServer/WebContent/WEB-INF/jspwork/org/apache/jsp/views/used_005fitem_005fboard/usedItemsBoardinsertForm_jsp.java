/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.58
 * Generated at: 2022-03-20 08:40:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.used_005fitem_005fboard;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class usedItemsBoardinsertForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/views/used_item_board/../common/header.jsp", Long.valueOf(1647761816615L));
    _jspx_dependants.put("/views/used_item_board/../common/category.jsp", Long.valueOf(1647764314874L));
    _jspx_dependants.put("/views/used_item_board/../common/footer.jsp", Long.valueOf(1647626898531L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("	.container{\r\n");
      out.write("		display: flex;\r\n");
      out.write("		width: 100vw;\r\n");
      out.write("	}\r\n");
      out.write("	form{\r\n");
      out.write("		margin: 0 auto;\r\n");
      out.write("    	width: 70vw;\r\n");
      out.write("	}\r\n");
      out.write("	.whole{\r\n");
      out.write("		display: flex;\r\n");
      out.write("		padding: 15px;\r\n");
      out.write("		border-bottom: 1px solid black;\r\n");
      out.write("	}\r\n");
      out.write("	.info{\r\n");
      out.write("		margin-right: 30px;\r\n");
      out.write("	}\r\n");
      out.write("	#imagin{\r\n");
      out.write("		display: flex;\r\n");
      out.write("		justify-content: center;\r\n");
      out.write("		align-items: center;\r\n");
      out.write("		background-color: gray;\r\n");
      out.write("		width: 100px;\r\n");
      out.write("		height: 100px;\r\n");
      out.write("		text-align: center;\r\n");
      out.write("	}\r\n");
      out.write("	.article{\r\n");
      out.write("		display: flex;\r\n");
      out.write("	}\r\n");
      out.write("	.article1{\r\n");
      out.write("		margin: 10px;\r\n");
      out.write("	}\r\n");
      out.write("	#contentImg1, #contentImg2, #contentImg3, #contentImg4, #contentImg5, \r\n");
      out.write("	#contentImg6, #contentImg7, #contentImg8, #contentImg9, #titleImg{\r\n");
      out.write("		width: 100px;\r\n");
      out.write("		height: 100px;\r\n");
      out.write("		margin-top: 0;\r\n");
      out.write("		display: none;\r\n");
      out.write("	}\r\n");
      out.write("	#contentImg1, #contentImg2, #contentImg3, #contentImg4, #contentImg5,\r\n");
      out.write("	#contentImg6, #contentImg7, #contentImg8, #contentImg9{\r\n");
      out.write("		margin-left: 0;\r\n");
      out.write("	}\r\n");
      out.write("	#title{\r\n");
      out.write("		width: 15em;\r\n");
      out.write("	}\r\n");
      out.write("	.titleline{\r\n");
      out.write("		margin-right: 94px;\r\n");
      out.write("	}\r\n");
      out.write("	.priceline{\r\n");
      out.write("		margin-right: 89px;\r\n");
      out.write("	}\r\n");
      out.write("	.productline{\r\n");
      out.write("		margin-right: 47px;\r\n");
      out.write("	}\r\n");
      out.write("	.categoryline{\r\n");
      out.write("		margin-right: 55px;\r\n");
      out.write("	}\r\n");
      out.write("	.itempline{\r\n");
      out.write("		margin-right: 45px;\r\n");
      out.write("	}\r\n");
      out.write("	.but{\r\n");
      out.write("		text-align: end;\r\n");
      out.write("	}\r\n");
      out.write("	#ret{\r\n");
      out.write("		border: 0px;\r\n");
      out.write("		border-radius: 3px;\r\n");
      out.write("		background-color: gainsboro;\r\n");
      out.write("		height: 25px;\r\n");
      out.write("	}\r\n");
      out.write("	#add{\r\n");
      out.write("		border: 0px;\r\n");
      out.write("		border-radius: 3px;\r\n");
      out.write("		background-color: #993333;\r\n");
      out.write("		color: whitesmoke;\r\n");
      out.write("		height: 25px;\r\n");
      out.write("	}\r\n");
      out.write("	input[type = \"file\"]{\r\n");
      out.write("		display: none;\r\n");
      out.write("	}\r\n");
      out.write("	.checkarea{\r\n");
      out.write("		width: 50vw;\r\n");
      out.write("		display: flex;\r\n");
      out.write("		justify-content: space-between;\r\n");
      out.write("	}\r\n");
      out.write("	.product{\r\n");
      out.write("		font-size: 11px;\r\n");
      out.write("	}\r\n");
      out.write("	.count{\r\n");
      out.write("		display: flex;\r\n");
      out.write("		flex-direction: column-reverse;\r\n");
      out.write("	}\r\n");
      out.write("	#item-explain{\r\n");
      out.write("		resize: none;\r\n");
      out.write("		width: 40vw;\r\n");
      out.write("	}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"../../resources/css/common/common.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header id=\"main_header\">\n");
      out.write("        <div id=\"header\">\n");
      out.write("            <div id=\"logo\"><a href=\"/\"><img src=\"../../resources/images/logo.gif\" alt=\"메인로고이미지\"></a></div>\n");
      out.write("            <div id=\"search\">\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("            <div id=\"header_top_right\">\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath());
      out.write("/loginPage\" class=\"login_page\">로그인</a>\n");
      out.write("                /\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath());
      out.write("/sign_up\" class=\"insert_user\">회원가입</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <nav id=\"main_nav\">\n");
      out.write("            <div class=\"nav\">\n");
      out.write("                <div class=\"category\">\n");
      out.write("                    <div class=\"category_box\">\n");
      out.write("                        <i class=\"berger1\"></i>\n");
      out.write("                        <i class=\"berger2\"></i>\n");
      out.write("                        <i class=\"berger3\"></i>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <ul>\n");
      out.write("                    <li><div onmouseover=\"add()\" onmouseleave=\"remove()\"><a href=\"<!--이동할 jsp가 위치한 경로입력 할 것-->\">시세</a></div></li>\n");
      out.write("                    <li><div onmouseover=\"add()\" onmouseleave=\"remove()\"><a href=\"<!--이동할 jsp가 위치한 경로입력 할 것-->\">경매</a></div></li>\n");
      out.write("                    <li><div onmouseover=\"add()\" onmouseleave=\"remove()\"><a href=\"");
      out.print(request.getContextPath() );
      out.write("/communitypage.do\">커뮤니티</a></div></li>\n");
      out.write("                    <li><div onmouseover=\"add()\" onmouseleave=\"remove()\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/usedBoardList.do\">중고거래</a></div></li>\n");
      out.write("                    <li><div onmouseover=\"add()\" onmouseleave=\"remove()\"><a href=\"");
      out.print(request.getContextPath() );
      out.write("/eventpage.do\">이벤트</a></div></li>\n");
      out.write("                    <li><div onmouseover=\"add()\" onmouseleave=\"remove()\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/serviceCenter.do\">고객센터</a></div></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("    </header>\n");
      out.write("    <script src=\"../../resources/library/jquery-3.6.0.min.js\"></script>\n");
      out.write("    <script src=\"../../resources/js/common/common.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("	<div class=\"container\">\r\n");
      out.write("		<form class=\"item\" action=\"insertUsed.do\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("			<h1>중고거래 등록하기</h1>\r\n");
      out.write("			<div>\r\n");
      out.write("				<div class=\"whole\">\r\n");
      out.write("					<div class=\"titleline\">\r\n");
      out.write("						<h3>제목<a style=\"color: red;\">*</a></h3>\r\n");
      out.write("					</div>\r\n");
      out.write("					<input type=\"text\" id=\"title\" placeholder=\"제목을 입력하세요\" maxlength=\"40\">\r\n");
      out.write("					<div>ㅤ</div>\r\n");
      out.write("					<div class=\"count\">\r\n");
      out.write("						<div>\r\n");
      out.write("							<small><c id=\"countone\"> 0</c>/40</small>\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				\r\n");
      out.write("				<div class=\"whole\">\r\n");
      out.write("					<div class=\"categoryline\">\r\n");
      out.write("						<h3>카테고리<a style=\"color: red;\">*</a></h3>\r\n");
      out.write("					</div>\r\n");
      out.write("					");
      out.write('\r');
      out.write('\n');

	boolean bol = false;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"../../resources/css/filter/filter.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<div id=\"category\">\r\n");
      out.write("		<div id=\"categorydiv\">\r\n");
      out.write("			<select id=\"large\">\r\n");
      out.write("				<option value=\"대분류\">대분류</option>\r\n");
      out.write("			</select>\r\n");
      out.write("			<select id=\"middle\">\r\n");
      out.write("				<option value=\"중분류\">중분류</option>\r\n");
      out.write("			</select>\r\n");
      out.write("			<select id=\"small\">\r\n");
      out.write("				<option value=\"소분류\">소분류</option>\r\n");
      out.write("			</select>\r\n");
      out.write("		</div>	\r\n");
      out.write("		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n");
      out.write("		<script>\r\n");
      out.write("			// 대분류를 선택시 아래에 표시\r\n");
      out.write("			\r\n");
      out.write("\r\n");
      out.write("			// 중분류 선택시 아래에 표시\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("			// 소분류 선택시 아래에 표시\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("		</script>\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("				</div>\r\n");
      out.write("				\r\n");
      out.write("				<div class=\"whole\">\r\n");
      out.write("					<div class=\"info\">\r\n");
      out.write("						<h3>이미지 선택<a style=\"color: red;\">*</a></h3>\r\n");
      out.write("						<small>\r\n");
      out.write("							최대 10개의<br>\r\n");
      out.write("							이미지 가능\r\n");
      out.write("						</small>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div>\r\n");
      out.write("						<div class=\"article\">\r\n");
      out.write("							<div id=\"imagin\">\r\n");
      out.write("								<div id=\"camera\">\r\n");
      out.write("									<img src=\"https://img.icons8.com/material-rounded/24/000000/camera--v2.png\"/>\r\n");
      out.write("									<br>\r\n");
      out.write("									이미지 선택\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("							<img id=\"titleImg\" class=\"article1\">\r\n");
      out.write("							<img id=\"contentImg1\" class=\"article1\">\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"article\">\r\n");
      out.write("							<img id=\"contentImg2\" class=\"article1\">\r\n");
      out.write("							<img id=\"contentImg3\" class=\"article1\">\r\n");
      out.write("							<img id=\"contentImg4\" class=\"article1\">\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"article\">\r\n");
      out.write("							<img id=\"contentImg5\" class=\"article1\">\r\n");
      out.write("							<img id=\"contentImg6\" class=\"article1\">\r\n");
      out.write("							<img id=\"contentImg7\" class=\"article1\">\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"article\">\r\n");
      out.write("							<img id=\"contentImg8\" class=\"article1\">\r\n");
      out.write("							<img id=\"contentImg9\" class=\"article1\">\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"whole\">\r\n");
      out.write("					<div class=\"productline\">\r\n");
      out.write("						<h3>상품 상태<a style=\"color: red;\">*</a></h3>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div>\r\n");
      out.write("						<input type=\"radio\" id=\"new\" value=\"새상품\" name=\"productStatus\" checked> 새상품 <input type=\"radio\" value=\"중고상품\" name=\"productStatus\" id=\"used\"> 중고 상품 \r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				\r\n");
      out.write("				<div class=\"whole\">\r\n");
      out.write("					<div class=\"priceline\">\r\n");
      out.write("						<h3>가격<a style=\"color: red;\">*</a></h3>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"checkarea\">\r\n");
      out.write("						<div>\r\n");
      out.write("							<input type=\"text\" id=\"price\" placeholder=\"가격을 입력해주세요\">원\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"checkline\">\r\n");
      out.write("							<div class=\"product\">\r\n");
      out.write("								<input type=\"checkbox\" id=\"one\" value=\"바로 결제\" name=\"payments\"> 바로 결제 <input type=\"checkbox\" id=\"two\" value=\"만나서 결제\" name=\"payments\" checked> 만나서 결제 \r\n");
      out.write("							</div>\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div>\r\n");
      out.write("					<div class=\"whole\">\r\n");
      out.write("						<div class=\"itempline\">\r\n");
      out.write("							<h3>상품 설명<a style=\"color: red;\">*</a></h3>\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"itempline\">\r\n");
      out.write("							<textarea id=\"item-explain\" cols=\"30\" rows=\"10\" placeholder=\"내용을 입력하세요.\" maxlength=\"2000\"></textarea>\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"count\">\r\n");
      out.write("							<div>\r\n");
      out.write("								<small><c id=\"counter\">0</c>/2000</small>\r\n");
      out.write("							</div>\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>	\r\n");
      out.write("				<div class=\"filearea\">\r\n");
      out.write("					<input type=\"file\" name=\"file1\" id=\"file1\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file2\" id=\"file2\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file3\" id=\"file3\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file4\" id=\"file4\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file5\" id=\"file5\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file6\" id=\"file6\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file7\" id=\"file7\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file8\" id=\"file8\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file9\" id=\"file9\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("					<input type=\"file\" name=\"file10\" id=\"file10\" accept='image/*' onchange=\"loadImg(this, i);\">\r\n");
      out.write("				</div>	\r\n");
      out.write("				<div>ㅤ</div>\r\n");
      out.write("				<div class=\"but\">\r\n");
      out.write("					<input id=\"ret\" type=\"button\" value=\"취소하기\"> <input id=\"add\" type=\"submit\" value=\"작성하기\" onclick=\"location.ef='");
      out.print( request.getContextPath() );
      out.write("/usedInsertBoard.do'\">\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</form>\r\n");
      out.write("	</div>\r\n");
      out.write("	<script>\r\n");
      out.write("		//결제 방식을 두가지 모드 체크 해제 했을 경우 알림\r\n");
      out.write("		$(\"#one\").click(function(){\r\n");
      out.write("			if($(\"#one\").is(':checked') == false & $(\"#two\").is(':checked') == false){\r\n");
      out.write("				alert(\"결제방식은 한가지 이상이여야 합니다.\")\r\n");
      out.write("				$(\"#two\").focus().prop(\"checked\",true)\r\n");
      out.write("			}\r\n");
      out.write("		})\r\n");
      out.write("		$(\"#two\").click(function(){\r\n");
      out.write("			if($(\"#one\").is(':checked') == false & $(\"#two\").is(':checked') == false){\r\n");
      out.write("				alert(\"결제방식은 한가지 이상이여야 합니다.\")\r\n");
      out.write("				$(\"#two\").focus().prop(\"checked\",true)\r\n");
      out.write("			}\r\n");
      out.write("		})\r\n");
      out.write("		\r\n");
      out.write("		// 제목 40자 제한\r\n");
      out.write("		$(function(){\r\n");
      out.write("            $(\"#title\").keydown(function(){\r\n");
      out.write("                var inputLength = $(this).val().length;\r\n");
      out.write("                var remain = 20-inputLength\r\n");
      out.write("                $(\"#countone\").html(inputLength)\r\n");
      out.write("                \r\n");
      out.write("				if(remain <= 0){\r\n");
      out.write("					alert(\"제목은 20자만 가능합니다.\")\r\n");
      out.write("					$(\"#title\").focus();\r\n");
      out.write("				}\r\n");
      out.write("            })\r\n");
      out.write("        })\r\n");
      out.write("        \r\n");
      out.write("		// 상품 설명 4000자 제한\r\n");
      out.write("		$(function(){\r\n");
      out.write("            $(\"#item-explain\").keydown(function(){\r\n");
      out.write("                var inputLength = $(this).val().length;\r\n");
      out.write("                var remain = 2000-inputLength\r\n");
      out.write("                $(\"#counter\").html(inputLength)\r\n");
      out.write("\r\n");
      out.write("				if(remain <= 0){\r\n");
      out.write("					alert(\"제목은 20자만 가능합니다.\")\r\n");
      out.write("					$(\"#item-explain\").focus();\r\n");
      out.write("				}\r\n");
      out.write("            })\r\n");
      out.write("        })\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("		//let count = 0; // 1, 파일ID file1 fil2\r\n");
      out.write("		let i = 1;\r\n");
      out.write("		// 카운트로 파일 넘버를 고를 수 있고 카운트가 10이 될 시 알림 울림\r\n");
      out.write("		$(\"#imagin\").click(function() {\r\n");
      out.write("			console.log($(\"#file\"+i).val())\r\n");
      out.write("			console.log(i)\r\n");
      out.write("			console.log($(\"#contentImg9\").is('[src]'))\r\n");
      out.write("			if($(\"#contentImg9\").is('[src]') == false){\r\n");
      out.write("				for(i; i <= 10 ; i++){\r\n");
      out.write("					if($(\"#file\"+i).val() == ''){\r\n");
      out.write("						$(\"#file\"+i).click();\r\n");
      out.write("						break;\r\n");
      out.write("					}\r\n");
      out.write("				}\r\n");
      out.write("			}\r\n");
      out.write("			if($(\"#contentImg9\").is('[src]') === true){\r\n");
      out.write("				alert(\"이미지는 10장 까지만 가능합니다.\")\r\n");
      out.write("			}\r\n");
      out.write("		})\r\n");
      out.write("\r\n");
      out.write("		// 파일 로드 후 이미지를 보여줌\r\n");
      out.write("		function loadImg(inputFile, num){\r\n");
      out.write("			console.log(inputFile.files.length )\r\n");
      out.write("			if(inputFile.files.length == 1){\r\n");
      out.write("				var reader = new FileReader(); // https://developer.mozilla.org/ko/docs/Web/API/FileReader 사이트 참고\r\n");
      out.write("				reader.readAsDataURL(inputFile.files[0]);\r\n");
      out.write("				\r\n");
      out.write("				reader.onload = function(e){\r\n");
      out.write("					switch(num){\r\n");
      out.write("					case 1 : $(\"#titleImg\").show(); $(\"#titleImg\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 2 : $(\"#contentImg1\").show(); $(\"#contentImg1\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 3 : $(\"#contentImg2\").show(); $(\"#contentImg2\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 4 : $(\"#contentImg3\").show(); $(\"#contentImg3\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 5 : $(\"#contentImg4\").show(); $(\"#contentImg4\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 6 : $(\"#contentImg5\").show(); $(\"#contentImg5\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 7 : $(\"#contentImg6\").show(); $(\"#contentImg6\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 8 : $(\"#contentImg7\").show(); $(\"#contentImg7\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 9 : $(\"#contentImg8\").show(); $(\"#contentImg8\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 10 : $(\"#contentImg9\").show(); $(\"#contentImg9\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					}\r\n");
      out.write("				}\r\n");
      out.write("			}/*else if(inputFile.files.length > 1){\r\n");
      out.write("				var reader = new FileReader(); // https://developer.mozilla.org/ko/docs/Web/API/FileReader 사이트 참고\r\n");
      out.write("				for(let i = 0 ; i < 10 ; i++){\r\n");
      out.write("					reader.readAsDataURL(inputFile.files[i]);\r\n");
      out.write("					switch(i){\r\n");
      out.write("					case 1 : $(\"#titleImg\").show(); $(\"#titleImg\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 2 : $(\"#contentImg1\").show(); $(\"#contentImg1\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 3 : $(\"#contentImg2\").show(); $(\"#contentImg2\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 4 : $(\"#contentImg3\").show(); $(\"#contentImg3\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 5 : $(\"#contentImg4\").show(); $(\"#contentImg4\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 6 : $(\"#contentImg5\").show(); $(\"#contentImg5\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 7 : $(\"#contentImg6\").show(); $(\"#contentImg6\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 8 : $(\"#contentImg7\").show(); $(\"#contentImg7\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 9 : $(\"#contentImg8\").show(); $(\"#contentImg8\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					case 10 : $(\"#contentImg9\").show(); $(\"#contentImg9\").attr(\"src\", e.target.result); break;\r\n");
      out.write("					}\r\n");
      out.write("				}\r\n");
      out.write("			}*/\r\n");
      out.write("		}\r\n");
      out.write("	</script>\r\n");
      out.write("	<div>\r\n");
      out.write("		");
      out.write("\n");
      out.write("    <!DOCTYPE html>\n");
      out.write("    <html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>Document</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../../resources/css/common/common.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <footer id=\"footer\">\n");
      out.write("            <div id=\"footer_content\">\n");
      out.write("                <div>\n");
      out.write("                    <span>발표시키지마시조</span>\n");
      out.write("                    |\n");
      out.write("                    <span>팀장 : 노한서</span>\n");
      out.write("                    |\n");
      out.write("                    <span>팀원 : 조현</span>\n");
      out.write("                    |\n");
      out.write("                    <span>팀원 : 김태연</span>\n");
      out.write("                    |\n");
      out.write("                    <span>팀원 : 최윤종</span>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <span>세미 프로젝트</span>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <span>노한서(팀장) : </span>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <span>조현 : </span>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <span>김태연 : </span>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <span>최윤종 : </span>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <span>Copyright © 2022.02.22-2022.04.03 KH Information Educational Institute All Right Reserved</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"logo\"><a href=\"/\"><img src=\"../../resources/images/logo.gif\" alt=\"로고이미지\"></a></div>\n");
      out.write("        </footer>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("        <script src=\"../../resources/library/jquery-3.6.0.min.js\"></script>\n");
      out.write("        <script src=\"../../resources/js/common/common.js\"></script>\n");
      out.write("    </body>\n");
      out.write("    </html>");
      out.write("\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
