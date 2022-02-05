package com.koltsov.cakes.manager.datagenerator.data;

import lombok.Data;

@Data
public class CountStatistic {
    private Integer success = 0;
    private Integer failed = 0;

    public void success() {
        success += 1;
    }

    public void fail() {
        failed += 1;
    }
}
