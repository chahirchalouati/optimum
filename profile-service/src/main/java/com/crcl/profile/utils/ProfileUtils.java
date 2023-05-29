package com.crcl.profile.utils;

import com.crcl.common.dto.UserDto;
import com.crcl.profile.domain.*;
import com.crcl.profile.dto.ProfileDto;
import org.jetbrains.annotations.NotNull;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        var occupation = new Occupation()
                .setTitle(entry.getKey())
                .setEmployerName(entry.getValue())
                .setStartDate(LocalDateTime.of(2015, 1, 1, 10, 20))
                .setEndDate(LocalDateTime.of(2021, 1, 1, 10, 20));
        profile.setOccupation(occupation);

        var interests = createRandomObjects(RandomUtils.nextInt(0, 30), () -> new Interest()
                .setName(INTERESTS.get(RandomUtils.nextInt(0, INTERESTS.size())))
                .setCategory(new InterestCategory()));
        profile.setInterests(interests);

        var workExperience = createRandomObjects(RandomUtils.nextInt(0, 30), () -> {
                    var occupationEntry = occupationMap.entrySet().stream().findAny().get();
                    return new WorkExperience()
                            .setTitle(occupationEntry.getKey())
                            .setEmployerName(occupationEntry.getValue())
                            .setLocation(location)
                            .setStartDate(LocalDateTime.of(2015, 1, 1, 10, 20))
                            .setEndDate(LocalDateTime.of(2021, 1, 1, 10, 20));
                }
        );
        profile.setWorkExperience(workExperience);

        var languages = createRandomObjects(RandomUtils.nextInt(0, 30), () -> new Language()
                .setName(LANGUAGES.get(RandomUtils.nextInt(0, LANGUAGES.size())))
                .setProficiency(Arrays.stream(Proficiency.values()).findAny().get()));
        profile.setLanguages(languages);

        var skills = createRandomObjects(RandomUtils.nextInt(0, 30), () -> {
            var skill = new Skill().setName(SKILLS.get(RandomUtils.nextInt(0, SKILLS.size())));
            var proficiency = Arrays.stream(Proficiency.values()).findAny().get();
            return new UserSkill()
                    .setSkill(skill)
                    .setProficiency(proficiency);
        });
        profile.setSkills(skills);


        var awards = createRandomObjects(RandomUtils.nextInt(0, 30), () -> {
            var name = AWARDS.get(RandomUtils.nextInt(0, AWARDS.size()));
            return new Award()
                    .setName(name)
                    .setDate(LocalDateTime.now())
                    .setIssuer("IEEE");
        });
        profile.setAwards(awards);
        var volunteer = new VolunteerExperience()
                .setOrganization("Red Cross")
                .setRole("Volunteer")
                .setStartDate(LocalDateTime.of(2018, 1, 1, 10, 20))
                .setEndDate(LocalDateTime.of(2019, 1, 1, 10, 20));

        profile.setVolunteerExperience(Set.of(volunteer));

        return profile;
    }

    @NotNull
    public static String getAvatar(UserDto user) {
        return user.getGender()
                .equals("MALE") ? DEFAULT_MALE_AVATAR : DEFAULT_FEMALE_AVATAR;
    }

    public static <T> Set<T> createRandomObjects(int count, int startFrom, Supplier<T> object) {
        return IntStream.range(startFrom, count)
                .mapToObj(operand -> object.get())
                .collect(Collectors.toSet());
    }

    public static <T> Set<T> createRandomObjects(int count, Supplier<T> object) {
        return createRandomObjects(count, 0, object);
    }
}
