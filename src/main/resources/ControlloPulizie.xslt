<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	extension-element-prefixes="date" xmlns:date="http://exslt.org/dates-and-times"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes" />

	<xsl:attribute-set name="title">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">14pt</xsl:attribute>
		<xsl:attribute name="text-align">center</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="header">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
		<xsl:attribute name="font-style">italic</xsl:attribute>
		<xsl:attribute name="text-align">center</xsl:attribute>
		<xsl:attribute name="line-height">16pt</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="text">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
		<xsl:attribute name="line-height">18pt</xsl:attribute>
		<xsl:attribute name="text-align">center</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="important-text">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
		<xsl:attribute name="text-align">center</xsl:attribute>
		<xsl:attribute name="line-height">22pt</xsl:attribute>
		<xsl:attribute name="font-weight">bold</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="bold_text" use-attribute-sets="text">
		<xsl:attribute name="font-weight">bold</xsl:attribute>
	</xsl:attribute-set>

	<xsl:template name="VoidLine5pt">
		<fo:block white-space-collapse="false" font-size="5pt">&#x00A0;</fo:block>
	</xsl:template>

	<xsl:template name="VoidLine14pt">
		<fo:block white-space-collapse="false" font-size="14pt">&#x00A0;</fo:block>
	</xsl:template>

	<xsl:template match="parameters">
		<fo:root>
			<fo:layout-master-set>
				<!-- Set horizontal page -->
				<fo:simple-page-master master-name="A4-landscape" margin-bottom="0.5cm" margin-left="0.75cm" margin-right="0.75cm" margin-top="0.5cm" page-height="8.5in" page-width="11in">
					<fo:region-body region-name="xsl-region-body" margin-bottom=".5in" margin-top=".50in" />
					<fo:region-after region-name="xsl-region-after" extent=".5in" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-landscape">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block xsl:use-attribute-sets="title">
						<xsl:value-of select="./footer/title" />
					</fo:block>
					<fo:block xsl:use-attribute-sets="text">
						<xsl:value-of select="./footer/subtitle" />
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<fo:block xsl:use-attribute-sets="title">
							Scheda 5 CONTROLLO PULIZIE
				        </fo:block>
						<xsl:call-template name="VoidLine14pt" />

						<fo:table width="100%" table-layout="fixed">						
							<fo:table-column column-number="1" column-width="25%" />
							<fo:table-column column-number="2" column-width="25%" />
							<fo:table-column column-number="3" column-width="25%" />
							<fo:table-column column-number="4" column-width="25%" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell column-number="1">
										<fo:block />
									</fo:table-cell>
									<fo:table-cell column-number="2">
										<fo:block />
									</fo:table-cell>
									<fo:table-cell column-number="3">
										<fo:block xsl:use-attribute-sets="header">
											Mese: 
											<fo:inline font-weight="bold">
												<xsl:value-of select="./month" />
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell column-number="4">
										<fo:block xsl:use-attribute-sets="header">
											Anno: 
											<fo:inline font-weight="bold">
												<xsl:value-of select="./year" />
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>

						<xsl:call-template name="VoidLine14pt" />
						
						<fo:block xsl:use-attribute-sets="important-text">
							Indicare nella tabella, con una X, le attrezzaure e i locali dove sono state effettuate le operazioni di pulizia.
						</fo:block>
						<xsl:call-template name="VoidLine5pt" />

						<fo:table width="100%" border="1pt solid #000000" table-layout="fixed">
							<fo:table-column column-number="1" column-width="16.3%" />
							<fo:table-column column-number="2" column-width="2.7%" />
							<fo:table-column column-number="3" column-width="2.7%" />
							<fo:table-column column-number="4" column-width="2.7%" />
							<fo:table-column column-number="5" column-width="2.7%" />
							<fo:table-column column-number="6" column-width="2.7%" />
							<fo:table-column column-number="7" column-width="2.7%" />
							<fo:table-column column-number="8" column-width="2.7%" />
							<fo:table-column column-number="9" column-width="2.7%" />
							<fo:table-column column-number="10" column-width="2.7%" />
							<fo:table-column column-number="11" column-width="2.7%" />
							<fo:table-column column-number="12" column-width="2.7%" />
							<fo:table-column column-number="13" column-width="2.7%" />
							<fo:table-column column-number="14" column-width="2.7%" />
							<fo:table-column column-number="15" column-width="2.7%" />
							<fo:table-column column-number="16" column-width="2.7%" />
							<fo:table-column column-number="17" column-width="2.7%" />
							<fo:table-column column-number="18" column-width="2.7%" />
							<fo:table-column column-number="19" column-width="2.7%" />
							<fo:table-column column-number="20" column-width="2.7%" />
							<fo:table-column column-number="21" column-width="2.7%" />
							<fo:table-column column-number="22" column-width="2.7%" />
							<fo:table-column column-number="23" column-width="2.7%" />
							<fo:table-column column-number="24" column-width="2.7%" />
							<fo:table-column column-number="25" column-width="2.7%" />
							<fo:table-column column-number="26" column-width="2.7%" />
							<fo:table-column column-number="27" column-width="2.7%" />
							<fo:table-column column-number="28" column-width="2.7%" />
							<fo:table-column column-number="29" column-width="2.7%" />
							<fo:table-column column-number="30" column-width="2.7%" />
							<fo:table-column column-number="31" column-width="2.7%" />
							<fo:table-column column-number="32" column-width="2.7%" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="solid 1px black" column-number="1">
										<fo:block xsl:use-attribute-sets="header">
											DATA
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="2">
										<fo:block xsl:use-attribute-sets="header">
											1
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="3">
										<fo:block xsl:use-attribute-sets="header">
											2
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="4">
										<fo:block xsl:use-attribute-sets="header">
											3
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="5">
										<fo:block xsl:use-attribute-sets="header">
											4
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="6">
										<fo:block xsl:use-attribute-sets="header">
											5
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="7">
										<fo:block xsl:use-attribute-sets="header">
											6
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="8">
										<fo:block xsl:use-attribute-sets="header">
											7
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="9">
										<fo:block xsl:use-attribute-sets="header">
											8
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="10">
										<fo:block xsl:use-attribute-sets="header">
											9
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="11">
										<fo:block xsl:use-attribute-sets="header">
											10
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="12">
										<fo:block xsl:use-attribute-sets="header">
											11
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="13">
										<fo:block xsl:use-attribute-sets="header">
											12
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="14">
										<fo:block xsl:use-attribute-sets="header">
											13
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="15">
										<fo:block xsl:use-attribute-sets="header">
											14
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="16">
										<fo:block xsl:use-attribute-sets="header">
											15
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="17">
										<fo:block xsl:use-attribute-sets="header">
											16
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="18">
										<fo:block xsl:use-attribute-sets="header">
											17
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="19">
										<fo:block xsl:use-attribute-sets="header">
											18
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="20">
										<fo:block xsl:use-attribute-sets="header">
											19
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="21">
										<fo:block xsl:use-attribute-sets="header">
											20
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="22">
										<fo:block xsl:use-attribute-sets="header">
											21
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="23">
										<fo:block xsl:use-attribute-sets="header">
											22
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="24">
										<fo:block xsl:use-attribute-sets="header">
											23
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="25">
										<fo:block xsl:use-attribute-sets="header">
											24
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="26">
										<fo:block xsl:use-attribute-sets="header">
											25
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="27">
										<fo:block xsl:use-attribute-sets="header">
											26
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="28">
										<fo:block xsl:use-attribute-sets="header">
											27
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="29">
										<fo:block xsl:use-attribute-sets="header">
											28
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="30">
										<fo:block xsl:use-attribute-sets="header">
											29
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="31">
										<fo:block xsl:use-attribute-sets="header">
											30
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="32">
										<fo:block xsl:use-attribute-sets="header">
											31
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<xsl:for-each select="./item">
									<fo:table-row>
										<fo:table-cell border="solid 1px black" text-align="center" column-number="1">
											<fo:block xsl:use-attribute-sets="text">
												<xsl:value-of select="./description" />
											</fo:block>
										</fo:table-cell>

										<xsl:for-each select="./infos/info">
											<fo:table-cell border="solid 1px black" text-align="center" column-number="{./day + 1}">
												<fo:block xsl:use-attribute-sets="text">
													<xsl:if test="./isPresent='true'">
														<xsl:text>X</xsl:text>
													</xsl:if>
													<xsl:if test="./isPresent='false'">
														<xsl:text></xsl:text>
													</xsl:if>
												</fo:block>
											</fo:table-cell>
										</xsl:for-each>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>

						<xsl:call-template name="VoidLine14pt" />
						<xsl:call-template name="VoidLine14pt" />

						<fo:table width="100%" table-layout="fixed">						
							<fo:table-column column-number="1" column-width="33%" />
							<fo:table-column column-number="2" column-width="33%" />
							<fo:table-column column-number="3" column-width="33%" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell column-number="1">
										<fo:block />
									</fo:table-cell>
									<fo:table-cell column-number="2">
										<fo:block />
									</fo:table-cell>
									<fo:table-cell column-number="3">
										<fo:block xsl:use-attribute-sets="header">
											Firma Responsabile HACCP
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>