package com.example.barbers.recycle;


//regular Java class
public class BarbersProfile {
    //properties
    private String tvAboutUsTitle;
    private String tvAboutUsinfo;
    private String tvProductsTitle;
    private String tvBioTitle;
    private String tvBioInfo;


    public BarbersProfile(String tvAboutUsTitle, String tvAboutUsinfo, String tvProductsTitle, String tvBioTitle, String tvBioInfo) {
        this.tvAboutUsTitle = tvAboutUsTitle;
        this.tvAboutUsinfo = tvAboutUsinfo;
        this.tvProductsTitle = tvProductsTitle;
        this.tvBioTitle = tvBioTitle;
        this.tvBioInfo = tvBioInfo;
    }

    public String getTvAboutUsTitle() {
        return tvAboutUsTitle;
    }

    public void setTvAboutUsTitle(String tvAboutUsTitle) {
        this.tvAboutUsTitle = tvAboutUsTitle;
    }

    public String getTvAboutUsinfo() {
        return tvAboutUsinfo;
    }

    public void setTvAboutUsinfo(String tvAboutUsinfo) {
        this.tvAboutUsinfo = tvAboutUsinfo;
    }

    public String getTvProductsTitle() {
        return tvProductsTitle;
    }

    public void setTvProductsTitle(String tvProductsTitle) {
        this.tvProductsTitle = tvProductsTitle;
    }

    public String getTvBioTitle() {
        return tvBioTitle;
    }

    public void setTvBioTitle(String tvBioTitle) {
        this.tvBioTitle = tvBioTitle;
    }

    public String getTvBioInfo() {
        return tvBioInfo;
    }

    public void setTvBioInfo(String tvBioInfo) {
        this.tvBioInfo = tvBioInfo;
    }

    @Override
    public String toString() {
        return "BarbersProfile{" +
                "tvAboutUsTitle=" + tvAboutUsTitle +
                ", tvAboutUsinfo=" + tvAboutUsinfo +
                ", tvProductsTitle=" + tvProductsTitle +
                ", tvBioTitle=" + tvBioTitle +
                ", tvBioInfo=" + tvBioInfo +
                '}';
    }
}
