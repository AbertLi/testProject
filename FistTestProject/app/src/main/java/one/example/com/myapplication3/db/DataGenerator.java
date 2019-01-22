package one.example.com.myapplication3.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.db.entity.FamilyEntity;
import one.example.com.myapplication3.db.entity.PersonEntity;

/*
有sister姐妹，son儿子，daughter女儿，grandfather爷爷，外公，grandmother奶奶，外婆，aunt姑姑，阿姨，uncle舅舅，叔叔，大伯，husband丈夫，wife妻子，cousin堂哥姐弟妹，表哥姐弟妹
 */
public class DataGenerator {
    private static String TAG = "DataGenerator   ";
    private static final String[] name = {"Linda", "Andy", "Alan", "Adam", "Jack", "Mary", "Fox", "Albert", "Jason", "Abraham"};

    private static final String[] family = {"mother", "Father", "sister", "son", "daughter", "grandfather", "grandmother", "aunt", "uncle", "wife", "cousin"};

    private static final String[] like = {"play card 打扑克", "playing computer game 玩电脑游戏", "surf the Internet 上网", "mahjong 麻将 handicraft手工艺", "music appreciation音乐欣赏", "play badminton 羽毛球",
            "play volleyball 排球", "play tennis 网球", "play table tennis 乒乓球", "swimming 游泳", "ski jumping competition 跳高滑雪比赛"};


    public static List<PersonEntity> generatePersons() {
        List<PersonEntity> listPerson = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < name.length; i++) {
            PersonEntity bean = new PersonEntity( name[i], String.valueOf( rnd.nextInt( 50 ) + 22 ) );
            bean.setId( 100 + i );
            listPerson.add( bean );
        }
        Logs.iprintln( TAG, "person size=" + listPerson.size() );
        return listPerson;
    }


    public static List<FamilyEntity> generateFamilyForPersons(List<PersonEntity> listPersion) {
        List<FamilyEntity> listFamily = new ArrayList<>();
        Random rnd = new Random();
        int num = 0;
        for (int i = 0; i < listPersion.size(); i++) {
            PersonEntity personBean = listPersion.get( i );
            int len = rnd.nextInt( family.length );
            for (int j = 0; j < len; j++) {
                num++;
                FamilyEntity familyEtity = new FamilyEntity();
                familyEtity.setId( num );
                familyEtity.setPersonId( personBean.getId() );
                familyEtity.setText( personBean.getName() + "'s " + family[j] );
                familyEtity.setAge( "" + (rnd.nextInt( 30 ) + 20) );
                familyEtity.setLike( like[rnd.nextInt( like.length )] );
                listFamily.add( familyEtity );
            }
        }
        Logs.iprintln( TAG, "family size=" + listFamily.size() );
        return listFamily;
    }
}
