package Thymely;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "thyme2")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name= "date")
    private Date today;

    public User(){}

    public User(String name, String email, String today1) throws ParseException {
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today=df.parse(today1);
        this.name=name;
        this.email=email;
        this.today=today;
    }

    public User(String name, String email, Date today){
        this.name=name;
        this.email=email;
        this.today=today;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "User (Name: " + this.name + ", email: " + this.email+")";
    }

    public static User create(String name, String email, Date today) {
        User e = new User();
        e.setName(name);
        e.setEmail(email);
        e.setToday(today);
        return e;
    }
    public static User create(String name, String email, String today) throws ParseException {
        User e = new User();
        e.setName(name);
        e.setEmail(email);
        e.setToday(today);
        return e;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public void setToday(String today1) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today=df.parse(today1);
        this.today = today;
    }


//    public Pet(String name, String owner, String species, String sex, String birth1, String death1) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//        Date birth= df.parse(birth1);
//        Date death=df.parse(death1);
//        this.name=name;
//        this.owner=owner;
//        this.species=species;
//        this.sex=sex.charAt(0);
//        this.birth=birth;
//        this.death=death;
//    }
//    public Pet(String name, String owner, String species, Character sex, String birth1, String death1) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//        Date birth= df.parse(birth1);
//        Date death=df.parse(death1);
//        this.name=name;
//        this.owner=owner;
//        this.species=species;
//        this.sex=sex;
//        this.birth=birth;
//        this.death=death;
//    }
//
//    public Pet(String name, String owner, String species, String sex, Date birth1, Date death1){
//        this.name=name;
//        this.owner=owner;
//        this.species=species;
//        this.sex=sex.charAt(0);
//        this.birth=birth1;
//        this.death=death1;
//    }
//    public Pet(String name, String owner, String species, Character sex, Date birth1, Date death1){
//        this.name=name;
//        this.owner=owner;
//        this.species=species;
//        this.sex=sex;
//        this.birth=birth1;
//        this.death=death1;
//    }
}
