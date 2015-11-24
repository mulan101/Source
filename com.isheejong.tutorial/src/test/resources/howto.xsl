<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="html" version="1.0" encoding="utf-8"
		indent="yes"
		doctype-public="PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
		<xsl:strip-space elements="*"/>
	<xsl:template match="policies">
		<html xmlns='http://www.w3.org/1999/xhtml' lang='ko' xml:lang='ko'>
			<head>
				<title>[올레닷컴] 개인정보수집, 이용약관 - :: 고객만족, 뛰고 또 뛰겠소 d	ododo olleh ::
				</title>
				<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
				<meta name='author' content='olleh' />
				<meta name='keywords' content='olleh' />
				<meta name='description' content='olleh' />
				<style>* { font-family:'돋움',Dotum,sans-serif; font-size:12px;
					margin:0; padding:0; line-height:16px;}ul,li {
					list-style:none;}.terms2011sp { width:auto;
					height:auto;}.terms2011sp
					h1 { margin-top:30px; padding:0 15px 7px 7px; color:#de1a22;
					font-size:14px; line-height:16px; letter-spacing:-1px;}/* 1장 */
					.terms2011sp h2 { margin:0; padding:13px 15px 0 15px;
					color:#3d3d3d;
					font-size:14px; line-height:16px; letter-spacing:-1px;}/* 1절 */
					.terms2011sp h3 { margin:0; padding:13px 15px 5px 15px;
					color:#3d3d3d; font-size:12px; line-height:16px;}/* 1조 */ /* 일반 텍스트
					*/ .terms2011sp p { margin:0; padding:0 15px 0 15px;
					line-height:18px; color:#6d6d6d;}/* 1~9:한자릿수 */ .lst_typesp {
					width:auto; padding:5px 15px 2px 25px !important; list-style:none;
					color:#6d6d6d;}.lst_typesp li { padding-left:15px;
					padding-bottom:3px; color:#6d6d6d; font-size:12px;
					line-height:16px;}.lst_typesp li span { display:block; float:left;
					width:14px; color:#6d6d6d; font-size:12px; margin:0 0 0 -14px;
					_margin:0 -3px 0 -7px;}.lst_typesp li ul.subWp01 { width:auto;
					list-style:none;}.lst_typesp li ul.subWp01 li { margin:0;
					padding:2px
					0 0 0; color:#9d9d9d !important; font-size:11px
					!important;}.lst_typesp li ul.subWp01 li.pointsp { margin:0;
					padding:2px 0 0 0; color:#de1a22 !important; font-size:11px
					!important;}.lst_typesp li ul.subWp01 li.pointsp span.bltRedsp {
					margin:0; padding:0; color:#de1a22 !important; font-size:12px
					!important;}/* 10 ~ 99:두자리수,가,나,다 같은 종류 */ .lst_typesp li.nsp {
					padding:0 15px 2px 17px !important; color:#6d6d6d; font-size:12px;
					line-height:16px;}.lst_typesp li.nsp span { display:block;
					float:left; width:17px; color:#6d6d6d; font-size:12px; margin:0 0 0
					-17px; _margin:0 -3px 0 -9px;}.lst_typesp li.nsp ul.subWp01 {
					width:auto; list-style:none;}.lst_typesp li.nsp ul.subWp01 li {
					margin:0; padding:2px 0 0 0; color:#9d9d9d !important;
					font-size:11px
					!important;}.lst_typesp li.nsp ul.subWp01 li.pointsp { margin:0;
					padding:2px 0 0 0; color:#de1a22 !important; font-size:11px
					!important;}.lst_typesp li.nsp ul.subWp01 li.pointsp span.bltRedsp
					{
					margin:0; padding:0; color:#de1a22 !important; font-size:12px
					!important;}/* 모양이 들어간 숫자:ex) ① */ .lst_type17sp { width:auto;
					padding:0 15px 0 15px; list-style:none; color:#6d6d6d;}.lst_type17sp
					li { padding-left:15px; padding-bottom:3px; color:#6d6d6d;
					font-size:12px; line-height:16px;}.lst_type17sp li span {
					display:block; float:left; width:15px; color:#6d6d6d;
					font-size:12px;
					margin:0 0 0 -15px; _margin:0 -3px 0 -7px;}/* 1 depth */
					.lst_type17sp li ul.subWp01 { width:auto;
					list-style:none;}.lst_type17sp li ul.subWp01 li { margin:0;
					padding:2px 0 0 0; color:#6d6d6d !important; font-size:12px
					!important;}.lst_type17sp li ul.subWp01 li.titlesp { margin:0;
					padding:13px 0 0 0; color:#3d3d3d !important; font-size:12px
					!important; font-weight:bold;}.lst_type17sp li ul.subWp01
					li.titleSubTxt { padding:2px 0 0 11px; color:#6d6d6d;
					font-size:12px;
					line-height:16px;}.lst_type17sp li ul.subWp01 li.titleSubTxt span {
					display:block; float:left; width:10px; color:#6d6d6d; font-size:12px;
					margin:0 0 0 -10px; _margin:0 -3px 0 -5px;}/* 2 depth */
					.lst_type17sp li ul.subWp01 li ul.subWp02 { width:auto;
					list-style:none;}.lst_type17sp li ul.subWp01 li ul.subWp02 li {
					margin:0; padding:2px 0 0 0; color:#9d9d9d !important;
					font-size:11px
					!important;}.lst_type17sp li ul.subWp01 li ul.subWp02 li.pointsp {
					margin:0; padding:2px 0 0 14px; color:#de1a22 !important;
					font-size:11px !important;}.lst_type17sp li ul.subWp01 li ul.subWp02
					li.pointsp span.bltRedsp { margin:0; padding:0; color:#de1a22
					!important; font-size:12px !important;}/* 모양이 들어간 숫자:ex) (1) */
					.lst_type20sp { width:auto; padding:0 15px 0 20px; list-style:none;
					color:#6d6d6d;}.lst_type20sp li { padding-left:15px;
					padding-bottom:3px; color:#6d6d6d; font-size:12px;
					line-height:16px;}.lst_type20sp li span { display:block;
					float:left;
					width:20px; color:#6d6d6d; font-size:12px; margin:0 0 0 -20px;
					_margin:0 -3px 0 -12px;}/* 1 depth */ .lst_type20sp li ul.subWp01 {
					width:auto; list-style:none;}.lst_type20sp li ul.subWp01 li {
					margin:0; padding:2px 0 0 0; color:#6d6d6d !important;
					font-size:12px
					!important;}.lst_type20sp li ul.subWp01 li.titlesp { margin:0;
					padding:13px 0 0 0; color:#3d3d3d !important; font-size:12px
					!important; font-weight:bold;}.lst_type20sp li ul.subWp01
					li.titleSubTxt { padding:2px 0 0 11px; color:#6d6d6d; font-size:12px;
					line-height:16px;}.lst_type20sp li ul.subWp01 li.titleSubTxt span {
					display:block; float:left; width:10px; color:#6d6d6d;
					font-size:12px;
					margin:0 0 0 -10px; _margin:0 -3px 0 -5px;}/* 2 depth */
					.lst_type20sp li ul.subWp01 li ul.subWp02 { width:auto;
					list-style:none;}.lst_type20sp li ul.subWp01 li ul.subWp02 li {
					margin:0; padding:2px 0 0 14px; color:#9d9d9d !important;
					font-size:11px !important;}.lst_type20sp li ul.subWp01 li
					ul.subWp02
					li.pointsp { margin:0; padding:2px 0 0 14px; color:#de1a22
					!important; font-size:11px !important;}.lst_type20sp li ul.subWp01 li
					ul.subWp02 li.pointsp span.bltRedsp { margin:0; padding:0;
					color:#de1a22 !important; font-size:12px !important;}/*
					1~9,(1)~(9):한자릿수 ->2depth */ .lst_type20spdepth { width:auto;
					padding:0 15px 0 20px; list-style:none;
					color:#6d6d6d;}.lst_type20spdepth li { padding-left:15px;
					padding-bottom:3px; padding-top:4px; color:#3d3d3d; font-size:12px;
					line-height:16px;}.lst_type20spdepth li span { display:block;
					float:left; width:20px; color:#3d3d3d; font-size:12px; margin:0 0 0
					-20px; _margin:0 -3px 0 -12px;}/* 1 depth */ .lst_type20spdepth li
					ul.subWp01 { width:auto; list-style:none;}.lst_type20spdepth li
					ul.subWp01 li { margin:0; padding:4px 0 0 0; color:#6d6d6d
					!important; font-size:12px !important;}.lst_type20spdepth li
					ul.subWp01 li.titlesp { margin:0; padding:3px 0 0 0; color:#6d6d6d
					!important; font-size:12px !important;}.lst_type20spdepth li
					ul.subWp01 li.titleSubTxt { padding:2px 0 0 12px; color:#6d6d6d;
					font-size:12px; line-height:16px;}.lst_type20spdepth li ul.subWp01
					li.titleSubTxt span { display:block; float:left; width:10px;
					color:#6d6d6d; font-size:12px; margin:0 0 0 -10px; _margin:0 -3px 0
					-5px;}/* 별표:캡션 */ p.cpTitle01sp { margin:0; padding:13px 15px 5px
					15px; color:#3d3d3d; font-size:12px; line-height:16px;
					font-weight:bold;}p.cpTitle02sp { margin:0; padding:2px 15px 5px
					15px; color:#3d3d3d; font-size:12px;
					line-height:16px;}p.cpTitle02spCol6d { margin:0; padding:2px 15px
					5px
					15px; color:#6d6d6d; font-size:12px; line-height:16px;}/* 폰트
					컬러:기본값일때. */ p.cpTitle03sp { margin:0; padding:2px 15px 5px 15px;
					color:#6d6d6d; font-size:12px; line-height:16px;}p.cpTitle03tb {
					margin:0; padding:20px 15px 5px 15px; color:#6d6d6d;
					font-size:12px;
					line-height:16px;}/* 테이블 하단 빨간색 문구 */ p.cpTxtPointsp01 { margin:0;
					padding:5px 0 0 15px; color:#de1a22 !important; font-size:11px
					!important;}/* 첫째줄 */ p.cpTxtPointsp01 span.bltRedsp { margin:0;
					padding:0; color:#de1a22 !important; font-size:12px !important;}/*
					첫째줄 */ p.cpTxtPointsp02 { margin:0; padding:0 0 0 15px; color:#de1a22
					!important; font-size:11px !important; line-height:12px;}/* 둘째줄 */
					p.cpTxtPointsp02 span.bltRedsp { margin:0; padding:0; color:#de1a22
					!important; font-size:12px !important; line-height:12px;}/* 둘째줄 */
					/*
					테이블 스타일 */ div.tblListsp { width:auto; height:auto; margin:0;
					padding:0 10px;}div.tblListsp table { table-layout:fixed;
					border-collapse:collapse; width:100%; height:auto; border-top:2px
					solid #e0e1e3; border-bottom:2px solid #e0e1e3;
					font-size:12px;}div.tblListsp table th { border-top:1px solid
					#e0e1e3; border-right:1px solid #ededed; background-color:#f9f9f9;
					padding:7px 0 5px 0; font-size:11px; color:#3d3d3d;}div.tblListsp
					table th.rtNonesp { border-top:1px solid #e0e1e3;
					border-right:none;
					background-color:#f9f9f9; padding:7px 0 5px 0; font-size:11px;
					color:#3d3d3d;}div.tblListsp table td { border-top:1px solid #e0e1e3;
					border-right:1px solid #ededed; padding:6px 0 4px 0; font-size:12px;
					color:#6d6d6d; text-align:center;}/* 테이블 기본 센터 정렬 */ div.tblListsp
					table td.acNonesp { border-top:1px solid #e0e1e3; border-right:none;
					padding:6px 0 4px 0; font-size:12px; color:#6d6d6d;
					text-align:center;}/* 테이블 기본 센터 정렬 - 오른쪽 보더 없음 */ div.tblListsp
					table
					td.al { border-top:1px solid #e0e1e3; border-right:1px solid #ededed;
					padding:6px 5px 4px 5px; font-size:12px; color:#6d6d6d;
					text-align:left;}/* 테이블 기본 왼쪽 정렬 */ div.tblListsp table td.alNonesp
					{
					border-top:1px solid #e0e1e3; border-right:none; padding:6px 5px 4px
					5px; font-size:12px; color:#6d6d6d; text-align:left;}/* 테이블 기본 센터 정렬
					- 오른쪽 보더 없음 */ /* 기타 여백 */ .terms2011spMarTp10 {
					margin-top:10px;}.terms2011spMarTp20 {
					margin-top:20px;}.terms2011spMarTp30 { margin-top:30px;}/* 컬러값 */
					.terms2011de1a22 { font-weight:bold; color: #de1a22;}
				</style>
			</head>
				<body>
					<div class='terms2011sp terms2011spMarTp30'>
						<h1>
							<xsl:value-of select="chapter/depth1/subject" />
						</h1>
						<h3><xsl:value-of select="chapter/depth1/depth2/subject" /></h3>
						<p> <xsl:value-of select="chapter/depth1/depth2/content" /></p>
						<ol class='lst_typesp'>
							<li class='nsp'><xsl:value-of select="chapter/depth1/depth2/depth3/subject" />
							</li>
						</ol>
					</div>
				</body>
		</html>
	</xsl:template>
</xsl:stylesheet>