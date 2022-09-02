package com.vince.xq.dataCompare;


import com.vince.xq.dataCompare.service.JobService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataCompareApplication.class)
class DataCompareApplicationTests {

    @Autowired
    private JobService jobService;

    @Test
    public void test() {
        //System.out.println(countCompareSql);
        //jobService.testStr();
    }

}
