package com.crcl.authentication.utils;

import com.crcl.authentication.domain.Gender;
import com.crcl.authentication.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator {

    private static final String[] FIRST_NAMES = {"Aaliyah", "Abigail", "Adam", "Adrian", "Ahmed", "Aiden", "Aisha", "Alessandra", "Alex", "Alexander", "Alexandra", "Alexis", "Alfred", "Ali", "Alia", "Alice", "Alicia", "Alina", "Alisa", "Alisha", "Alison", "Alisson", "Allison", "Amalia", "Amanda", "Amani", "Amara", "Amari", "Amelia", "Amir", "Amit", "Amira", "Amirah", "Amiyah", "Amy", "Ana", "Anastasia", "Andrea", "Andres", "Andrew", "Andy", "Angel", "Angela", "Angelina", "Anika", "Anisa", "Aniyah", "Ann", "Anna", "Annabelle", "Anne", "Annie", "Anouar", "Antoinette", "Antonio", "Ariana", "Arianna", "Arianne", "Ariel", "Ariella", "Arielle", "Armando", "Arnaud", "Aron", "Asher", "Ashley", "Ashton", "Astrid", "Athena", "Aubrey", "Audrey", "August", "Aurelia", "Aurora", "Austin", "Ava", "Avery", "Ayman", "Aynur", "Ayush", "Bailee", "Barbara", "Barry", "Basil", "Bastien", "Baylee", "Beatriz", "Belinda", "Bella", "Ben", "Benedict", "Benjamin", "Bennett", "Berenice", "Bernadette", "Bernard", "Beth", "Bethany", "Betsy", "Betty", "Bianca", "Bill", "Billy", "Blaire", "Blaise", "Blake", "Bobby", "Bonnie", "Brad", "Bradley", "Brady", "Braxton", "Brayden", "Breanna", "Bree", "Brenda", "Brennan", "Brent", "Brett", "Brian", "Brianna", "Bridget", "Brittany", "Brody", "Brooke", "Brooklyn", "Bruce", "Bryan", "Brynn", "Bryony", "Buddy", "Buffy", "Caitlin", "Caitlyn", "Calvin", "Cameron", "Camila", "Camille", "Camryn", "Candace", "Candice", "Cara", "Carla", "Carlos", "Carly", "Carmen", "Carol", "Caroline", "Carolyn", "Casey", "Cassandra", "Cassidy", "Catalina", "Catherine", "Cathy", "Cayden", "Cecilia", "Cedric", "Celeste", "Celia", "Celine", "Chad", "Chandler", "Chantal", "Charity", "Charlene", "Charles", "Charlie", "Charlotte", "Aaliyah", "Abigail", "Adam", "Adrian", "Ahmed", "Aiden", "Aisha", "Alessandra", "Alex", "Alexander", "Alexandra", "Alexis", "Alfred", "Ali", "Alia", "Alice", "Alicia", "Alina", "Alisa", "Alisha", "Alison", "Alisson", "Allison", "Amalia", "Amanda", "Amani", "Amara", "Amari", "Amelia", "Amir", "Amit", "Amira", "Amirah", "Amiyah", "Amy", "Ana", "Anastasia", "Andrea", "Andres", "Andrew", "Andy", "Angel", "Angela", "Angelina", "Anika", "Anisa", "Aniyah", "Ann", "Anna", "Annabelle", "Anne", "Annie", "Anouar", "Antoinette", "Antonio", "Ariana", "Arianna", "Arianne", "Ariel", "Ariella", "Arielle", "Armando", "Arnaud", "Aron", "Asher", "Ashley", "Ashton", "Astrid", "Athena", "Aubrey", "Audrey", "August", "Aurelia", "Aurora", "Austin", "Ava", "Avery", "Ayman", "Aynur", "Ayush", "Bailee", "Barbara", "Barry", "Basil", "Bastien", "Baylee", "Beatriz", "Belinda", "Bella", "Ben", "Benedict", "Benjamin", "Bennett", "Berenice", "Bernadette", "Bernard", "Beth", "Bethany", "Betsy", "Betty", "Bianca", "Bill", "Billy", "Blaire", "Blaise", "Blake", "Bobby", "Bonnie", "Brad", "Bradley", "Brady", "Braxton", "Brayden", "Breanna", "Bree", "Brenda", "Brennan", "Brent", "Brett", "Brian", "Brianna", "Bridget", "Brittany", "Brody", "Brooke", "Brooklyn", "Bruce", "Bryan", "Brynn", "Bryony", "Buddy", "Buffy", "Caitlin", "Caitlyn", "Calvin", "Cameron", "Camila", "Camille", "Camryn", "Candace", "Candice", "Cara", "Carla", "Carlos", "Carly", "Carmen", "Carol", "Caroline", "Carolyn", "Casey", "Cassandra", "Cassidy", "Catalina", "Catherine", "Cathy", "Cayden", "Cecilia", "Cedric", "Celeste", "Celia", "Celine", "Chad", "Chandler", "Chantal", "Charity", "Charlene", "Charles", "Charlie", "Charlotte", "Chase", "Everett", "Peyton"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Green", "Baker", "Adams", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales"};
    private static final String[] AVATARS = {"https://example.com/avatar1.png", "https://example.com/avatar2.png", "https://example.com/avatar3.png", "https://example.com/avatar4.png", "https://example.com/avatar5.png"};
    private static final Gender[] GENDERS = {Gender.MALE, Gender.FEMALE};

    private static final Random RANDOM = new Random();

    public static List<User> generateRandomUsers(int count, String username) {
        return generateRandomUsers(count, username, "password");
    }

    public static List<User> generateRandomUsers(int count, String username, String password) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setFirstName(FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)]);
            user.setLastName(LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)]);
            user.setUsername(username + i);
            user.setEmail(generateEmail(user.getFirstName(), user.getLastName()));
            user.setPassword(password);
            user.setAvatar(AVATARS[RANDOM.nextInt(AVATARS.length)]);
            user.setGender(GENDERS[RANDOM.nextInt(GENDERS.length)]);
            users.add(user);
        }
        return users;
    }

    public static String generateEmail(String firstName, String lastName) {
        Random rand = new Random();
        int num = rand.nextInt(10000);
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + num + "@email.com";
    }
}