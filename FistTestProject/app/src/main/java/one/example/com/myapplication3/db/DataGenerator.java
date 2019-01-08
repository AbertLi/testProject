package one.example.com.myapplication3.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import one.example.com.myapplication3.db.entity.FamilyEtity;
import one.example.com.myapplication3.db.entity.PersonBean;
/*
有sister姐妹，son儿子，daughter女儿，grandfather爷爷，外公，grandmother奶奶，外婆，aunt姑姑，阿姨，uncle舅舅，叔叔，大伯，husband丈夫，wife妻子，cousin堂哥姐弟妹，表哥姐弟妹
 */
public class DataGenerator {
    private static final String[] name = {"AAA", "BBB", "CCC", "DDD", "Jack", "Mary", "Fox", "Albert", "Jason", "FFF", "ggg"};

    private static final String[] family={"Mom","Father","sister","son","daughter","grandfather","grandmother","aunt","uncle","wife","cousin"};


    public static List<PersonBean> generatePersons() {
        List<PersonBean> listPerson = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < listPerson.size(); i++) {
            PersonBean bean = new PersonBean( name[i], String.valueOf( rnd.nextInt(50) + 22 ) );
            bean.setId( 100+i );
            listPerson.add( bean );
        }
        return listPerson;
    }


    public static List<FamilyEtity> generateFamilyForPersons(List<PersonBean> listPersion ) {
        List<FamilyEtity> listFamily = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i <listPersion.size() ; i++) {
            PersonBean personBean = listPersion.get( i );
            FamilyEtity familyEtity=new FamilyEtity();
            familyEtity.setPersonId(personBean.getId());
            familyEtity.setText(personBean.getName()+"'s "+family[rnd.nextInt(family.length)]);
            familyEtity.setAge( ""+(rnd.nextInt(30)+20) );
            listFamily.add( familyEtity );
        }
        return listFamily;
    }
}
