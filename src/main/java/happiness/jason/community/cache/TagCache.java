package happiness.jason.community.cache;

import happiness.jason.community.dto.TagDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO tagDTOProgram = new TagDTO();
        tagDTOProgram.setCategoryName("开发语言");
        tagDTOProgram.setTags(Arrays.asList("Java", "JavaScript", "PHP", "C#", "NodeJS", "HTML5", "python", "css", "golang", "c", "c++", "object-c", "ruby", "typescript"));
        tagDTOS.add(tagDTOProgram);

        TagDTO tagDTOFramwork = new TagDTO();
        tagDTOFramwork.setCategoryName("平台框架");
        tagDTOFramwork.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDTOS.add(tagDTOFramwork);

        TagDTO tagDTOServer = new TagDTO();
        tagDTOServer.setCategoryName("服务器");
        tagDTOServer.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(tagDTOServer);

        TagDTO tagDTODb = new TagDTO();
        tagDTODb.setCategoryName("数据库和缓存");
        tagDTODb.setTags(Arrays.asList("mySql", "nginx", "docker", "apache", "ubuntu", "centos", "缓存", "tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(tagDTODb);

        TagDTO tagDTOTool = new TagDTO();
        tagDTOTool.setCategoryName("开发工具");
        tagDTOTool.setTags(Arrays.asList("git", "github", "visula-studio-core", "vim", "sublime-text", "xcode", "intellij-idea", "eclipse", "maven", "ide", "svn", "visula-studio", "atom", "emacs", "textmate", "hg"));
        tagDTOS.add(tagDTOTool);

        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] splitTag = tags.split(",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        return Arrays.stream(splitTag).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
    }
}
