import com.vince.xq.project.common.DbTypeEnum;
import com.vince.xq.project.common.RunUtil;
import com.vince.xq.project.system.dbconfig.domain.Dbconfig;
import com.vince.xq.project.system.jobconfig.domain.Jobconfig;
import org.junit.jupiter.api.Test;

public class XqTest {
    @Test
    public void testSql() throws Exception {
        Dbconfig dbconfig = new Dbconfig();
        dbconfig.setType(DbTypeEnum.MySQL.getType());
        dbconfig.setUrl("jdbc:mysql://10.71.7.89:3306");
        dbconfig.setUserName("root");
        dbconfig.setPwd("123456");
        Jobconfig jobconfig = new Jobconfig();
        jobconfig.setOriginTableName("test_db.user_info");
        jobconfig.setOriginTablePrimary("id");
        jobconfig.setOriginTableFields("name,age");
        jobconfig.setToTableName("test_db.user_info_copy");
        jobconfig.setToTablePrimary("id");
        jobconfig.setToTableFields("name,age");

        RunUtil.run(dbconfig, jobconfig);
    }

    @Test
    public void strFormate() {
        String str = "%sxx%sxx%s";
        String str1 = String.format(str, "a", "b", "c");
        System.out.println(str1);
    }
}
