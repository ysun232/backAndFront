package Thymely.Preload;

import Thymely.Preload.RandomUtil;
import Thymely.User;
import Thymely.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Adds random amounts of data to mySQL, everytime creates a completely new table without having to go in there and do it manually
@Component
public class UserInitializer {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    void postConstruct() throws ParseException {
        userRepository.saveAll(createUsers());
    }

    private List<User> createUsers() throws ParseException {
        String[] names = {"James", "Arthur", "John", "Kevin", "Max"};
        String[] email = {"ubgiuber123@hotmail.com", "abcdefg@hotmail.com", "hijklmn@hotmail.com", "opqrstuvw@hotmail.com"};
        String[] date = {"2019-10-22", "2018-08-11", "2010-02-12", "2009-07-19", "2000-01-13", "2008-04-28"};
        List<User> users = new ArrayList<>();
        Date localDate = Date.from(RandomUtil.getDate(1970,2010).atStartOfDay(ZoneOffset.UTC).toInstant());

        for(int i=0; i<1000; i++){
            users.add(User.create(RandomUtil.getAnyOf(names), RandomUtil.getAnyOf(email), localDate));
//            users.add(User.create(RandomUtil.getAnyOf(names), RandomUtil.getAnyOf(email), RandomUtil.getAnyOf(date) ));
        }
        return users;
    }
}
