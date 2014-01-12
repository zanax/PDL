package models;

public class Chapter {

    private int id;
    private int course_id;
    private int language;
    private String title;
    private String chapter_description;
    private String chapterName;
    private String description;
    private String imgSrc;
    private String bgImgSrc;
    private String chapter_content;
    private boolean isActive;
    private String videoUrl;

    public Chapter() {
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChapter_description() {
        return chapter_description;
    }

    public void setChapter_description(String chapter_description) {
        this.chapter_description = chapter_description;
    }

    public Chapter(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getBgImgSrc() {
        return bgImgSrc;
    }

    public void setBgImgSrc(String bgImgSrc) {
        this.bgImgSrc = bgImgSrc;
    }

    public String getChapter_content() {
        return chapter_content;
    }

    public void setChapter_content(String chapter_content) {
        this.chapter_content = chapter_content;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
