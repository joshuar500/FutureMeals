package com.joshrincon.springyummly.yummlywrapper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {

    private String hostedLargeUrl;
    private String hostedMediumUrl;
    private String hostedSmallUrl;
    public String getHostedLargeUrl() {
        return this.hostedLargeUrl;
    }
    public void setHostedLargeUrl(String hostedLargeUrl) {
        this.hostedLargeUrl = hostedLargeUrl;
    }
    public String getHostedMediumUrl() {
        return this.hostedMediumUrl;
    }
    public void setHostedMediumUrl(String hostedMediumUrl) {
        this.hostedMediumUrl = hostedMediumUrl;
    }
    public String getHostedSmallUrl() {
        return this.hostedSmallUrl;
    }
    public void setHostedSmallUrl(String hostedSmallUrl) {
        this.hostedSmallUrl = hostedSmallUrl;
    }

}
