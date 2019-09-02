<%@page import="com.squirrel.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>golf-hi</title>


<link rel="stylesheet" type="text/css" href="/golfhi/css/style.min.css" />
<link rel="stylesheet" type="text/css" href="/golfhi/css/modules.css" />

<!-- Canonical URL usage -->
<link rel="canonical" href="https://aperitif.io/">

<!-- Facebook Open Graph -->
<meta property="og:url" content="https://aperitif.io/" />
<meta property="og:title"
	content="Aperitif | The web template generator" />
<meta property="og:description"
	content="Aperitif is a rapid web template generation tool." />
<meta property="og:image"
	content="https://aperitif.io/img/aperitif-facebook.png" />
<meta property="og:image:width" content="1200" />
<meta property="og:image:height" content="630" />

<!-- Twitter Cards -->
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@Aperitif">
<meta name="twitter:creator" content="@Aperitif">
<meta name="twitter:title"
	content="Aperitif - The web template generator">
<meta name="twitter:description"
	content="Aperitif is a rapid web template generation tool.">
<meta name="twitter:image"
	content="https://aperitif.io/img/aperitif-card.png">

<!-- Google Structured Data -->
<script type="application/ld+json">
	{
	"@context" : "http://schema.org",
	"@type" : "SoftwareApplication",
	"name" : "Aperitif",
	"image" : "https://aperitif.io/img/aperitif-logo.svg",
	"url" : "https://aperitif.io/",
	"author" : {
	  "@type" : "Person",
	  "name" : "Octavector"
	},
	"datePublished" : "2017-MM-DD",
	"applicationCategory" : "HTML"
	}
	</script>
</head>
<!-- End Head -->

<body class="default">

	<!--
START MODULE AREA 1: header1
-->
	<header class="MOD_HEADER1">
		<div data-layout="_r">
			<div data-layout="al16 de10" class="MOD_HEADER1_Title">
				<h1 class="MOD_HEADER1_TextLogo">GolfHi</h1>
				<p class="MOD_HEADER1_Slogan">
					<c:if test="${login != null}">
				안녕하세요 ${login.nickname} 님
				</c:if>
				</p>
			</div>
		</div>
	</header>
	<!--
END MODULE AREA 1: Header 1
-->

	<!--
START MODULE AREA 2: Menu 1
-->
	<section class="MOD_MENU" data-theme="_bgp">
		<div data-layout="_r" class="nopadding">
			<nav class="MOD_MENU_Nav">
				<p class="MOD_MENU_Title">Menu</p>
				<svg class="MOD_MENU_Button"
					xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
					width="30px" height="30px" viewBox="0 0 30 30"
					enable-background="new 0 0 30 30" xml:space="preserve">
        <rect width="30" height="6" />
        <rect y="24" width="30" height="6" />
        <rect y="12" width="30" height="6" />
      </svg>
				<ul class="AP_Menu_List">

					<!-- 반복 준비    -->
					<%
						{
							Object test = session.getAttribute("login");
							MemberDTO tmpdto = null;
							if (test != null)
								tmpdto = (MemberDTO) test;
							if (tmpdto != null)
								System.out.print("으아아 좀 : " + tmpdto.getRating());
						}
					%>
					<c:choose>
						<c:when test="${empty login}">
							<c:import var="menuInfo" url="/xml/mainNot_login.xml?a=a"
								charEncoding="UTF-8" />
						</c:when>
						<c:when test="${login.rating eq'U'}">
							<c:import var="menuInfo" url="/xml/mainRatingUser.xml?a=a"
								charEncoding="UTF-8" />
						</c:when>
						<c:when test="${login.rating eq'M'}">
							<c:import var="menuInfo" url="/xml/mainRatingManager.xml?a=a"
								charEncoding="UTF-8" />
						</c:when>
						<c:when test="${login.rating eq'A'}">
							<c:import var="menuInfo" url="/xml/mainRatingAdmin.xml?a=a"
								charEncoding="UTF-8" />
						</c:when>
					</c:choose>
					<!-- 로그인한 유저에 따라 보여줄 정보를 다르게 표기. 판단은   여기서 -->

					<c:import var="aaaa" url="/mainMenuXml"
						charEncoding="UTF-8" />
						${aaaa}
						
					<x:parse xml="${menuInfo}" var="output" />

					<x:forEach select="$output//Menu" var='Menu'>
						<li><a href='<x:out select="url"/>' data-theme="_bgp"><x:out
									select="name" /></a> <x:set var="test"
								select="string($Menu/sub_Menu)" /> <c:if
								test="${not empty test}">
								<ul>

									<x:forEach select="$Menu/sub_Menu" var='sub'>
										<li><a href='<x:out select="url"/>' data-theme="_bgpd"><x:out
													select="name" /> </a></li>
									</x:forEach>
								</ul>
							</c:if></li>
					</x:forEach>

				</ul>
			</nav>
		</div>
	</section>
	<!--
END MODULE AREA 2: Menu 1
-->

	<script type="text/javascript" src="/golfhi/js/index.js"></script>