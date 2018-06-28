/*
 * Copyright (c) 2018
 * Author : LuomingXu
 * Project Name : forJavaCurriculumDesign_Mybatis
 * File Name : Main.java
 * CreateTime: 2018/06/28 14:53:23
 * LastModifiedDate : 18-6-28 下午2:52
 */

import dto.CityModel;

import java.util.ArrayList;
import java.util.List;


public class Main
{
    private final static String mybatisPath = "mybatis/mybatis-config.xml";


    public static void main(String[] args)
    {
        System.out.println("start");
        try
        {
            CityModel city = new CityModel();
            city.setCountrycode("sdfjkd");
            city.setDistrict("fdskfjk");
            city.setId(1);
            city.setName("kdjfskdjfk");
            city.setPopulation(124234535);
            List<CityModel> models = new ArrayList<CityModel>();
            models = DAO.getModel("selectByPrimaryKey", 0, city, CityModel.class.getName());

            System.out.println("models size: " + models.size());
            for (CityModel item : models)
            {
                System.out.println(item);
            }
            models = DAO.getModel("selectTest", null, null, CityModel.class.getName());
            System.out.println("models size: " + models.size());
            for (CityModel item : models)
            {
                System.out.println(item);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
