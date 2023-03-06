package com.crcl.profile.utils;

import com.crcl.profile.domain.*;
import com.crcl.profile.dto.ProfileDto;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.*;

public class ProfileUtils {
    public static final String DEFAULT_MALE_AVATAR = "https://thumbs.dreamstime.com/b/default-avatar-photo-placeholder-profile-icon-eps-file-easy-to-edit-default-avatar-photo-placeholder-profile-icon-124557887.jpg";
    public static final String DEFAULT_FEMALE_AVATAR = "https://thumbs.dreamstime.com/b/default-female-avatar-profile-picture-icon-grey-woman-photo-placeholder-vector-illustration-88413637.jpg";
    public static final String DEFAULT_BG_IMAGE = "https://img.freepik.com/free-vector/blue-gradient-blank-background-business_53876-120508.jpg?w=2000";

    public static final List<String> INTERESTS = List.of("Music", "Sports", "Reading", "Cooking", "Traveling", "Photography", "Fashion", "Fitness", "Gaming", "Art", "Movies", "Singing", "Dancing", "Theater", "Writing", "Camping", "Hiking", "Fishing", "Swimming", "Skiing", "Snowboarding", "Scuba diving", "Skydiving", "Surfing", "Yoga", "Meditation", "Cycling", "Running", "Gardening", "Volunteering");
    public static final List<String> SKILLS = List.of("Programming", "Web development", "Project management", "Communication", "Teamwork", "Problem solving", "Critical thinking", "Creativity", "Leadership", "Time management", "Adaptability", "Decision making", "Negotiation", "Marketing", "Sales", "Customer service", "Accounting", "Finance", "Human resources", "Event planning", "Public relations", "Design", "Illustration", "Photography", "Videography", "Copywriting", "Editing", "Translation", "Teaching", "Coaching", "Mentoring");
    public static final List<String> AWARDS = List.of("Employee of the Month", "Outstanding Performance Award", "Best Team Player Award", "Salesperson of the Year", "Customer Service Excellence Award", "Innovation Award", "Best Employee of the Year", "Rookie of the Year", "Most Improved Employee", "Excellence in Leadership Award", "Excellence in Customer Satisfaction Award", "Excellence in Quality Award", "Excellence in Safety Award", "Excellence in Sales Award", "Excellence in Marketing Award", "Excellence in Technology Award", "Excellence in Innovation Award", "Excellence in Teamwork Award", "Excellence in Diversity and Inclusion Award", "Excellence in Corporate Social Responsibility Award", "Excellence in Environmental Sustainability Award", "Excellence in Health and Wellness Award", "Excellence in Learning and Development Award", "Excellence in Work-Life Balance Award", "Excellence in Ethics and Integrity Award", "Excellence in Collaboration Award", "Excellence in Problem Solving Award", "Excellence in Decision Making Award", "Excellence in Adaptability Award", "Excellence in Time Management Award");
    public static final List<String> LANGUAGES = Arrays.stream(Locale.getISOLanguages())
            .map(Locale::new)
            .map(Locale::getDisplayLanguage)
            .toList();
    public static final Map<String, String> occupationMap = new LinkedHashMap<>();

    static {
        occupationMap.put("Software Developer", "Information Technology");
        occupationMap.put("Accountant", "Finance");
        occupationMap.put("Nurse", "Healthcare");
        occupationMap.put("Teacher", "Education");
        occupationMap.put("Lawyer", "Legal");
        occupationMap.put("Mechanical Engineer", "Engineering");
        occupationMap.put("Sales Manager", "Business");
        occupationMap.put("Marketing Manager", "Marketing");
        occupationMap.put("Social Media Specialist", "Digital Marketing");
        occupationMap.put("Architect", "Construction");
        occupationMap.put("Real Estate Agent", "Real Estate");
        occupationMap.put("Pharmacist", "Pharmacy");
        occupationMap.put("Dentist", "Dentistry");
        occupationMap.put("Graphic Designer", "Design");
        occupationMap.put("Chef", "Culinary");
        occupationMap.put("Psychologist", "Psychology");
        occupationMap.put("Human Resources Manager", "Human Resources");
        occupationMap.put("Project Manager", "Project Management");
        occupationMap.put("Public Relations Specialist", "Public Relations");
        occupationMap.put("Journalist", "Media");
        occupationMap.put("Actor", "Entertainment");
        occupationMap.put("Athlete", "Sports");
        occupationMap.put("Biomedical Engineer", "Biomedical Engineering");
        occupationMap.put("Aerospace Engineer", "Aerospace Engineering");
        occupationMap.put("Geologist", "Geology");
        occupationMap.put("Meteorologist", "Meteorology");
        occupationMap.put("Chemist", "Chemistry");
        occupationMap.put("Physicist", "Physics");
        occupationMap.put("Mathematician", "Mathematics");
    }

    public static ProfileDto getDefaultProfile(UserDto user) {
        ProfileDto profile = new ProfileDto()
                .setUser(user)
                .setUsername(user.getUsername())
                .setAvatar(getAvatar(user))
                .setBackgroundImage(DEFAULT_BG_IMAGE);

        Address address = new Address()
                .setCity("New York")
                .setState("New York")
                .setCountry("USA");
        Location location = new Location()
                .setAddress(address);
        profile.setLocation(Set.of(location));


        Hometown hometown = new Hometown();
        hometown.setAddress(address);
        profile.setHometown(hometown);

        Map.Entry<String, String> entry = occupationMap.entrySet().stream().findAny().get();
        Occupation occupation = new Occupation()
                .setTitle(entry.getKey())
                .setEmployerName(entry.getValue())
                .setStartDate(LocalDateTime.of(2015, 1, 1, 10, 20))
                .setEndDate(LocalDateTime.of(2021, 1, 1, 10, 20));
        profile.setOccupation(occupation);

        Set<Interest> interests = new HashSet<>();
        Interest interest1 = new Interest();
        interest1.setName(INTERESTS.get(RandomUtils.nextInt(0, INTERESTS.size())));
        interest1.setCategory(new InterestCategory());
        interests.add(interest1);
        profile.setInterests(interests);

        Map.Entry<String, String> entry_1 = occupationMap.entrySet().stream().findAny().get();
        Map.Entry<String, String> entry_2 = occupationMap.entrySet().stream().findAny().get();
        Set<WorkExperience> workExperience = Set.of(

                new WorkExperience()
                        .setTitle(entry_1.getKey())
                        .setEmployerName(entry_1.getValue())
                        .setLocation(location)
                        .setStartDate(LocalDateTime.of(2015, 1, 1, 10, 20))
                        .setEndDate(LocalDateTime.of(2021, 1, 1, 10, 20)),

                new WorkExperience()
                        .setTitle(entry_2.getKey())
                        .setEmployerName(entry_2.getValue())
                        .setLocation(location)
                        .setStartDate(LocalDateTime.of(2021, 1, 1, 10, 20))
        );
        profile.setWorkExperience(workExperience);

        Set<Language> languages = Set.of(
                new Language()
                        .setName(LANGUAGES.get(RandomUtils.nextInt(0, LANGUAGES.size())))
                        .setProficiency(Arrays.stream(Proficiency.values()).findAny().get()),

                new Language()
                        .setName(LANGUAGES.get(RandomUtils.nextInt(0, LANGUAGES.size())))
                        .setProficiency(Arrays.stream(Proficiency.values()).findAny().get()),

                new Language()
                        .setName(LANGUAGES.get(RandomUtils.nextInt(0, LANGUAGES.size())))
                        .setProficiency(Arrays.stream(Proficiency.values()).findAny().get())
        );
        profile.setLanguages(languages);


        Set<UserSkill> skills = Set.of(new UserSkill()
                .setSkill(new Skill().setName(SKILLS.get(RandomUtils.nextInt(0, SKILLS.size()))))
                .setProficiency(Arrays.stream(Proficiency.values()).findAny().get()));
        profile.setSkills(skills);

//// Create a list of awards
//        List<Award> awards = new ArrayList<>();
//        Award award1 = new Award();
//        award1.setName("Best Paper Award");
//        award1.setDate(LocalDateTime.of(2020, 6, 15, 10, 20));
//        award1.setIssuer("IEEE");
//        Award award2 = new Award();
//        award2.setName("Employee of the Month");
//        award2.setDate(LocalDateTime.of(2019, 3, 1, 10, 20));
//        award2.setIssuer("Facebook");
//        awards.add(award1);
//        awards.add(award2);
//
//// Create a list of volunteer experiences
//        List<VolunteerExperience> volunteerExperience = new ArrayList<>();
//        VolunteerExperience volunteer1 = new VolunteerExperience();
//        volunteer1.setOrganization("Red Cross");
//        volunteer1.setRole("Volunteer");
//        volunteer1.setStartDate(LocalDateTime.of(2018, 1, 1, 10, 20));
//        volunteer1.setEndDate(LocalDateTime.of(2019, 1, 1, 10, 20));
//        VolunteerExperience volunteer2 = new VolunteerExperience();
//        volunteer2.setOrganization("UNICEF");
//        volunteer2.setRole("Volunteer");
//        volunteer2.setStartDate(LocalDateTime.of(2019, 1, 1, 10, 20));
//        volunteerExperience.add(volunteer1);
//        volunteerExperience.add(volunteer2);

        return profile;
    }

    @NotNull
    public static String getAvatar(UserDto user) {
        return user.getGender()
                .equals("MALE") ? DEFAULT_MALE_AVATAR : DEFAULT_FEMALE_AVATAR;
    }
}
