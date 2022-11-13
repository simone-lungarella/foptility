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

	<xsl:attribute-set name="body-text">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
		<xsl:attribute name="line-height">16pt</xsl:attribute>
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
				<fo:simple-page-master master-name="A4-portrait" page-height="29.7cm" page-width="21.0cm" margin="2cm">
					<fo:region-body region-name="xsl-region-body" margin-bottom=".5in" margin-top=".50in"/>
            		<fo:region-after region-name="xsl-region-after" extent=".5in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
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
						<xsl:call-template name="VoidLine5pt" />
						
						<fo:block xsl:use-attribute-sets="title">
							Scheda 12 TRACCIABILITÀ PRODOTTO FINITO
				        </fo:block>

						<xsl:call-template name="VoidLine14pt" />

						<fo:block xsl:use-attribute-sets="body-text">
							Tipo di prodotto: <xsl:value-of select="./productInfo/name" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Data di produzione: <xsl:value-of select="./productInfo/date" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Trattamento termico: <xsl:value-of select="./productInfo/cookingType" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Tempo (min): <xsl:value-of select="./productInfo/cookingTime" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Temperatura (°C): <xsl:value-of select="./productInfo/temperature" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Lotto tappi o coperchi: <xsl:value-of select="./productInfo/coverLot" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Formato da g/ml: <xsl:value-of select="./productInfo/size" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Lotto prodotto finito: <xsl:value-of select="./productInfo/productLot" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Numero confezioni ottenute: <xsl:value-of select="./productInfo/numberPacks" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Scadenza: <xsl:value-of select="./productInfo/expirationDate" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Lotto bottiglie o vasetti: <xsl:value-of select="./productInfo/lotBottles" />
						</fo:block>
						<fo:block xsl:use-attribute-sets="body-text">
							Note: <xsl:value-of select="./productInfo/notes" />
						</fo:block>

						<xsl:call-template name="VoidLine14pt" />

						<fo:block xsl:use-attribute-sets="title">
							Ingredienti utilizzati
				        </fo:block>
						<xsl:call-template name="VoidLine5pt" />

						<fo:table width="100%">
							<fo:table-column column-width="25%" />
							<fo:table-column column-width="25%" />
							<fo:table-column column-width="25%" />
							<fo:table-column column-width="25%" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="solid 1px black" column-number="1">
										<fo:block xsl:use-attribute-sets="header">
											Ingrediente
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="2">
										<fo:block xsl:use-attribute-sets="header">
											Quantità
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="3">
										<fo:block xsl:use-attribute-sets="header">
											Lotto
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" column-number="4">
										<fo:block xsl:use-attribute-sets="header">
											Fornitore
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:for-each select="./items/item">
									<fo:table-row>
										<fo:table-cell border="solid 1px black" text-align="center" column-number="1">
											<fo:block xsl:use-attribute-sets="text">
												<xsl:value-of select="./ingredient" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 1px black" text-align="center" column-number="2">
											<fo:block xsl:use-attribute-sets="text">
												<xsl:value-of select="./lot" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 1px black" text-align="center" column-number="3">
											<fo:block xsl:use-attribute-sets="text">
												<xsl:value-of select="./quantity" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 1px black" text-align="center" column-number="4">
											<fo:block xsl:use-attribute-sets="text">
												<xsl:value-of select="./supplier" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>