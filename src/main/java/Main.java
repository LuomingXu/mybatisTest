import dto.CityModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main
{
    private final static String mybatisPath = "mybatis/mybatis-config.xml";


    public static void main(String[] args)
    {
        System.out.println("start");
        try
        {
            CityModel city=new CityModel();
            city.setCountrycode("sdfjkd");
            city.setDistrict("fdskfjk");
            city.setId(1);
            city.setName("kdjfskdjfk");
            city.setPopulation(124234535);
            List<CityModel> models=new ArrayList<CityModel>();
            models = DAO.getModel("selectByPrimaryKey",0,city,CityModel.class.getName());

            System.out.println("models size: "+models.size());
            for(CityModel item : models)
            {
                System.out.println(item);
            }
            models = DAO.getModel("selectTest",null,null,CityModel.class.getName());
            System.out.println("models size: "+models.size());
            for(CityModel item : models)
            {
                System.out.println(item);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
