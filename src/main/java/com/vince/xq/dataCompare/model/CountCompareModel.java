package com.vince.xq.dataCompare.model;

public class CountCompareModel {
    long baseUv ;
    long verifyUv;
    long diffUv;

    public CountCompareModel(long baseUv, long verifyUv, long diffUv) {
        this.baseUv = baseUv;
        this.verifyUv = verifyUv;
        this.diffUv = diffUv;
    }

    public long getBaseUv() {
        return baseUv;
    }

    public void setBaseUv(long baseUv) {
        this.baseUv = baseUv;
    }

    public long getVerifyUv() {
        return verifyUv;
    }

    public void setVerifyUv(long verifyUv) {
        this.verifyUv = verifyUv;
    }

    public long getDiffUv() {
        return diffUv;
    }

    public void setDiffUv(long diffUv) {
        this.diffUv = diffUv;
    }
}
