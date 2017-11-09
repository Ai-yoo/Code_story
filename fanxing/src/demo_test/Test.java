package demo_test;


import com_daoImp.BaseDaoImp;
import com_domain.Address;
import com_domain.Users;
import java.lang.reflect.Field;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        BaseDaoImp usersDaoImp = new BaseDaoImp();
//        System.out.println("name".substring(0,1));
//        System.out.println(getSet("name"));
//
//        get(new Users());
        BaseDaoImp<Users> baseDaoImp = new BaseDaoImp<Users>();
//        Address a = new Address();
        Users users = new Users();
        Test.getField(users);
//        int id = 0;
//        baseDaoImp.update(users,"qaq",6);
//        users.setId(1111);
//        users.setName("wc");
//        users.setPassword("mimi");
//        users.setAid(1);
//        Users users1 = new Users();
//        List list= baseDaoImp.selectAll(a);
//        baseDaoImp.insert(users);
//        baseDaoImp.selectMap(users, users1, 1, 3);
//        List l = baseDaoImp.selectOne(users, 6);
//        baseDaoImp.selectMap(users,users1);
//        System.out.println(l.toString());
//        for (Object u : list) {
//            System.out.println(u.toString());
//        }
    }
    //            String type = field.getGenericType().toString();
    public static void getField(Object object) {
        //获得类
        Class clazz = object.getClass();
        // 获取实体类的所有属性信息，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }


}
