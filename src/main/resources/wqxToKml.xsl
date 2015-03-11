<?xml version="1.0" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="MonitoringLocation">
        <Placemark>
            <name><xsl:value-of select="MonitoringLocationIdentity/MonitoringLocationName"/></name>
            <styleUrl>%STYLE_URL_REFERENCE%</styleUrl>
            <ExtendedData>
                <Data name="Organization Formal Name">
                    <value>
                        <xsl:value-of select="OrganizationDescription/OrganizationFormalName"/>
                    </value>
                </Data>
                <Data name="Organization Identifier">
                    <value>
                        <xsl:value-of select="OrganizationDescription/OrganizationIdentifier"/>
                    </value>
                </Data>
                <Data name="Monitoring Location Identifier">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/MonitoringLocationIdentifier"/>
                    </value>
                </Data>
                <Data name="Monitoring Location Name">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/MonitoringLocationName"/>
                    </value>
                </Data>
                <Data name="Monitoring Location Type Name">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/MonitoringLocationTypeName"/>
                    </value>
                </Data>
                <Data name="Monitoring Location Description Text">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/MonitoringLocationDescriptionText"/>
                    </value>
                </Data>
                <Data name="HUC Eight Digit Code">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/HUCEightDigitCode"/>
                    </value>
                </Data>
                <Data name="Drainage Area Measure/Measure Value">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/ContributingDrainageAreaMeasure/MeasureValue"/>
                    </value>
                </Data>
                <Data name="Drainage Area Measure/Measure Unit Code">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/ContributingDrainageAreaMeasure/MeasureUnitCode"/>
                    </value>
                </Data>
                <Data name="AquiferTypeName">
                    <value>
                        <xsl:value-of select="WellInformation/AquiferTypeName"/>
                    </value>
                </Data>
                <Data name="AquiferName">
                    <value>
                        <xsl:value-of select="WellInformation/AquiferName"/>
                    </value>
                </Data>
                <Data name="Well Depth Measure/Measure Value">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/WellInformation/WellDepthMeasure/MeasureValue"/>
                    </value>
                </Data>
                <Data name="Well Depth Measure/Measure Unit Code">
                    <value>
                        <xsl:value-of select="MonitoringLocationIdentity/WellInformation/WellDepthMeasure/MeasureUnitCode"/>
                    </value>
                </Data>
            </ExtendedData>
            <Point>
                <coordinates>
                    <xsl:value-of select="MonitoringLocationGeospatial/LongitudeMeasure"/>,<xsl:value-of select="MonitoringLocationGeospatial/LatitudeMeasure"/>
                </coordinates>
            </Point>
        </Placemark>
    </xsl:template>
</xsl:stylesheet>