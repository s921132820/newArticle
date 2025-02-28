package com.my.articles.controller;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("articles")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("comment")
    public String showComment() {
        return "/articles/update_comment";
    }

    @GetMapping({"", "/"})
    public String showAllArticles(Model model) {
        List<ArticleDTO> articles = articleService.getAllArticle();
        model.addAttribute("articles", articles);
        return "/articles/show_all";
    }

    @GetMapping("{id}")
    public String showOneArticles(
            @PathVariable("id") Long id,
            Model model
    ) {
        ArticleDTO dto = articleService.getOneArticle(id);
        // 게시글의 id로 요청시 댓글 달려있는지 확인
        System.out.println(dto);
        model.addAttribute("dto", dto);

        return "/articles/show";
    }

    @GetMapping("new")
    public String newArticles() {
        return "/articles/new";
    }

    @PostMapping("create")
    public ModelAndView createArticle(ArticleDTO dto) {
        String url = "redirect:/articles";
        articleService.insertArticle(dto);
        return new ModelAndView(url);
    }



    // 게시글의 삭제
    @GetMapping("{id}/delete")
    public String deleteArticle(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {
        articleService.deleteArticle(id);
        String msg = "정상적으로 삭제되었습니다";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/articles";
    }

    // 수정 될 ID 받아서 수정 폼 보이는 작업
    @GetMapping("{id}/update")
    public String viewUpdateArticles(
            @PathVariable("id") Long id,
            Model model
    ) {
        model.addAttribute("dto", articleService.getOneArticle(id));
        return "/articles/update";
    }

    // 수정 작업
    @PostMapping("update")
    public String updateArticle(ArticleDTO dto) {
        String url = "redirect:/articles/" + dto.getId();
        log.info("======" + dto);
        articleService.updateArticle(dto);
        return url;
    }
}
