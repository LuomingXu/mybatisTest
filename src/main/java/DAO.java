/*
 * Copyright (c) 2018
 * Author : LuomingXu
 * Project Name : forJavaCurriculumDesign_Mybatis
 * File Name : DAO.java
 * CreateTime: 2018/06/28 14:53:31
 * LastModifiedDate : 18-6-28 下午2:52
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DAO<T>
{
    private final static String mybatisPath = "mybatis/mybatis-config.xml";

    private static void printNameLine(Exception e)
    {
        System.err.println(Thread.currentThread().getClass() +
                "--" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                "--" + Thread.currentThread().getStackTrace()[1].getLineNumber());
        e.printStackTrace();
    }

    /**
     * get class Field[] if className == null
     * return String variable Field[]
     *
     * @param className the class which you want get its variable
     * @return class's Filed[]
     */
    private static Field[] getVariable(String className)
    {
        try
        {
            Object obj = Class.forName(className).newInstance();
            Class c = obj.getClass();
            return c.getDeclaredFields();
        }
        catch (Exception e)
        {
            try
            {
                Object obj = Class.forName("String").newInstance();
                Class c = obj.getClass();
                return c.getDeclaredFields();
            }
            catch (Exception ee)
            {
                printNameLine(ee);
            }
        }

        return null;
    }

    /**
     * connect DB get model
     *
     * @param mapperID  id in mapper.xml
     * @param varIndex  class variable index
     * @param param     generics model which you could get data for you select statement
     * @param className generics's class full name
     * @param <T>       generics model name
     * @return model from DB
     */
    public static <T> List<T> getModel(String mapperID, Integer varIndex, T param, String className)
    {
        List<T> models = new ArrayList<T>();
        T model;
        Field[] fields = getVariable(className);

        //remove access check
        for (Field item : fields)
            item.setAccessible(true);

        try
        {
            InputStream inputStream = Resources.getResourceAsStream(mybatisPath);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = ssf.openSession();
            if (varIndex == null)
            {
                models = session.selectList(mapperID);
                return models;
            }
            else
            {
                int ID = Integer.parseInt(fields[varIndex].get(param).toString());
                model = session.selectOne(mapperID, ID);
                models.add(model);
            }
            return models;
        }
        catch (IOException ee)
        {
            printNameLine(ee);
        }
        catch (Exception e)
        {
            printNameLine(e);
        }

        return null;
    }
}
//    //reach each method
//    System.out.println("方法");
//    Method[] methods = c.getMethods();
//    for (Method m : methods)
//    {
//    System.out.println(m.getName());
//    }


