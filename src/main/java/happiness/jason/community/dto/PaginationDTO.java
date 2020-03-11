package happiness.jason.community.dto;

import happiness.jason.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.page = page;
        this.totalPage = totalPage;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        this.showPrevious = page != 1;
        this.showNext = !page.equals(totalPage);
        this.showFirstPage = !pages.contains(1);
        this.showLastPage = !pages.contains(totalPage);
    }
}
