package com.vince.xq.dataCompare.utils;

import com.vince.xq.dataCompare.antrl4.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaoqiu2017wy@163.com
 * @Date 2022/7/16
 */
public class HiveSqlMetadataParser extends HiveSqlBaseVisitor {
    private boolean flag = true;
    List<String> metaData=new ArrayList<>();



    @Override
    public Object visitSelect_list(HiveSqlParser.Select_listContext ctx) {
        int size = ctx.select_list_item().size();
        if (size > 0) {
            if (flag) {
                for (int i = 0; i < size; i++) {
                    if (ctx.select_list_item().get(i).select_list_alias() != null) {
                        //System.out.println(ctx.select_list_item().get(i).select_list_alias().ident().getText());
                        metaData.add(ctx.select_list_item().get(i).select_list_alias().ident().getText());
                    } else if(ctx.select_list_item().get(i).expr()!=null){
                        //System.out.println(ctx.select_list_item().get(i).expr().getText());
                       metaData.add(ctx.select_list_item().get(i).expr().getText());
                    } else if(ctx.select_list_item().get(i).select_list_asterisk()!=null){
                        metaData.add(ctx.select_list_item().get(i).select_list_asterisk().getText());
                    }

                }
                flag = false;
            }
        }
        return super.visitSelect_list(ctx);
    }

    public List<String> getMetaData(){
        System.out.println(metaData.size());
        System.out.println(metaData.toString());
        return metaData;
    }
}
