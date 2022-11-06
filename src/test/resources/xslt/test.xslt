<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	extension-element-prefixes="date" xmlns:date="http://exslt.org/dates-and-times"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes"/>

	<xsl:attribute-set name="title">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">10pt</xsl:attribute>
		<xsl:attribute name="text-align">center</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="header">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">10pt</xsl:attribute>
		<xsl:attribute name="font-style">italic</xsl:attribute>
		<xsl:attribute name="text-align">center</xsl:attribute>
	</xsl:attribute-set>
	
	<xsl:attribute-set name="text">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">12pt</xsl:attribute>
		<xsl:attribute name="line-height">22pt</xsl:attribute>
		<xsl:attribute name="text-align">justify</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="important-text">
		<xsl:attribute name="font-family">'Times New Roman', Times, serif</xsl:attribute>
		<xsl:attribute name="font-size">12pt</xsl:attribute>
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

	<xsl:template match="/parameters">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait" page-height="29.7cm" page-width="21.0cm" margin="2cm">
					<fo:region-body/>
        		</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
					<fo:block>

						<fo:block text-align="center" font-size="7pt">
							<fo:external-graphic width="80%" scaling="uniform" content-height="80%" content-width="scale-to-fit"  src="url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGIAAABHCAYAAAD8xJKLAAAACXBIWXMAAAsSAAALEgHS3X78AAAFRElEQVR4nO1ci1EbMRDdZFLAdcBRAZcKuFQAqSBOBXEqiKnAQwWGCuwODBUAFZhUcE4FzhyzYsShz+5qdbYZvZkdMzaWdNqf9iNDQUFBQUFBwWHj0wfgTwsAFQBsAeARX48OXzIuuMENcuEZSQM9I/4MxjFM6XGPr/acd4fGKA1GNLgZZwBQ498c9JvSb9AT/v3I/L4LlbWO0Hps5hiG2Uw8eA3rN3wOABsA2ClTBwALBkMvM6zBReuAhieD6yNqNAMTwv+uUMqHUlUjnRM2u5fW3zhWCDMcz4ydAzcA8DPT2CzMiJIzY0hOhdKvLY2XGbSVa3LVUeFGUBZL0ZQh+vEpY3foj6ioFZnwcAhMeMi02Ab9TL/B1A3pmJpBFaAYTZnPpo4lY7EzwuQaTn6xB0Zkc9IUTJiLDTGC6guoRHXIGoxYjrPdfnCldu0ZiWPeqET1RRqMkPg9NXC1IaTCmppA0T5tRoxilj573r8QjnfpeG+fEsU5ZbkwWmTtY4T0zHzueC/Hg1BzRanSfJv4/SQ0CWrcOSaWmjkfbRgPlzpXqkYlYZq4eJc2TZjxQojRnM1JnWs0uExTqjq7/EufpzkFgKuE9Hdvjr4qZWep8+0VqScNiulo8eSzDMy3xs+nCYm8lOfYezR99GdvC0frH0CJEZt9pwUQR+EfwOMjNEqYde5CSmaM5Yde4WLEved/uWhQM2YZizW5oLUHZLgqdFUm02Lqw66HPPEwa4tVvq2wnv0gtPXfCVXBUaAdhGlRx6wASv1dqqOuMN1jnwzXWAJgZy0OlRk7RmAnZYQENR55KZlmTk3lBbUge7qxJGCdqdODygwJIzgpFEAJ5xTQDM3tQThdHK2nS8JEoKFMZY0a9kvZ9/Rzfwt8PhcEZrExDRqpqbFwqthox0KOIlFIK6idJzZRKnKp+bh3WuFLg+fCFqVNMzXuqoGk4Cny3cXQrGisfWxGADLhWnG8s8Bn2om7uXL65tXMc3tffZ103KbiG0fjsBRjRe9tpkRgP+5diBE1qs5FpLPbxiNK4W0k+DKMyx1xa6YqfiiORUKrlPhbRxypVlOBr3vEgDuerzFBq0dqSC+nrqGPmOOEGn2eLZ6QfM5UK58Tk3qun/D5nKzHTJsRi0w20BdFajnSf5HPuRvoM8GjNBLkTmf4NEyjjh3TXsmZP8c+dbiWoYl7w/hcaYjYZklSA0OKHSJawZgh38ZthBgmKodB5is029e5EiaJfG2idqBzx411EsaaqTsUsolDUBa+9UskhkOhTGPq3NQsJvfEw0n8mXuDrXXD1YdqoE1v1p+TEbH7DNRLKj6inu4kmpfr4GLP8S5Kz8GEDbFmkDI+FZLuRW4zG5cJO1dAq+E0bVoSI/EU/8SNdSQnNGmp1UbryTg7/VtKv6tN3GBQ6qwl2U9pJN8JzFSFZifkm96MaReGpsIH3GIS75oZPDXClhvqNVs7QWnS7imXEreDK8s2KnyeE8al/2BRiCM164RawFRoKiinpNkIcVEqkU571EiUK8mmuC7ZJI55OHQm7FxO2lezbpBrIUdlVPWvJ29kq2uT4PRW+OsDVLOnlbTMBdEvGEz2KGFL4YamRus5yRtXxSp0N0gTLBBp14eHeMQs5yoh7cwtBlHvyVGLYyFc+ebi/iiK6WA7TzQ3YP0cT0/3aN40mgoax+noGcc3Jx6N34uyGWM098wyyUOmBdt0NH7BzBwTqwhjbMnLfRtnh6/mWD16dzeitfZltcd17A2xMm3BSDjWexkFBQUFBQUFBQUFBQUFBQUFBQUFHwIA8B/c+rI+UXt80wAAAABJRU5ErkJggg==')"/>
						</fo:block>
						<xsl:call-template name="VoidLine5pt"/>
						<fo:block xsl:use-attribute-sets="title">
							GENERATED PDF FROM XSLT TEMPLATE
				        </fo:block>

						<xsl:call-template name="VoidLine14pt"/>
						<fo:table table-layout="fixed" width="100%">
							<fo:table-column column-width="55%"/>
							<fo:table-column column-width="45%"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="center" column-number="1">
										<fo:block/>
									</fo:table-cell>
									<fo:table-cell column-number="2">
										<fo:block text-align="left" xsl:use-attribute-sets="header" >
											<fo:inline><xsl:value-of select="./header"/> </fo:inline>	
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						
						<xsl:call-template name="VoidLine14pt"/>
						<xsl:call-template name="VoidLine14pt"/>
						<fo:block xsl:use-attribute-sets="bold_text">
							Object: Example template
				        </fo:block>
						
						<xsl:call-template name="VoidLine14pt"/>
						<xsl:call-template name="VoidLine14pt"/>
	
						<fo:block xsl:use-attribute-sets="text">
							This template is made in <xsl:value-of select="./today-date"/> by <fo:inline xsl:use-attribute-sets="bold_text"><xsl:value-of select="./author"/></fo:inline>, feel free to use it without any limit.
						</fo:block>
						<xsl:call-template name="VoidLine14pt"/>
						
						<xsl:call-template name="VoidLine5pt"/>
						<fo:block xsl:use-attribute-sets="text" linefeed-treatment="preserve">
							<fo:inline><xsl:value-of select="./notes" disable-output-escaping="yes"/> </fo:inline>	
						</fo:block>
						
						<xsl:call-template name="VoidLine14pt"/>
						<xsl:call-template name="VoidLine14pt"/>
						
						<fo:table table-layout="fixed" width="100%">
							<fo:table-column column-width="50%"/>
							<fo:table-column column-width="50%"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="center" column-number="1">
										<fo:block text-align="center" xsl:use-attribute-sets="text">Date: <xsl:value-of select="./today-date"/></fo:block>
									</fo:table-cell>
									<fo:table-cell column-number="2">
										<fo:block text-align="center" xsl:use-attribute-sets="bold_text"> Simone </fo:block>
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