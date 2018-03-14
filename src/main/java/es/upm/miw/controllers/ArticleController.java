package es.upm.miw.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.upm.miw.documents.core.Article;
import es.upm.miw.dtos.ArticleOutputDto;
import es.upm.miw.repositories.core.ArticleRepository;

@Controller
public class ArticleController {

	private static final String VARIOUS_CODE = "1";

	@Autowired
	private ArticleRepository articleRepository;

	public Optional<ArticleOutputDto> readArticle(String code) {
		ArticleOutputDto articleOutputDto = this.articleRepository.findMinimumByCode(code);
		if (articleOutputDto == null && code.length() < 5) {
			articleOutputDto = this.articleRepository.findMinimumByCode(VARIOUS_CODE);
		}
		if (articleOutputDto == null) {
			return Optional.empty();
		} else {
			return Optional.of(articleOutputDto);
		}
	}

	public ArticleOutputDto postFastArticle(ArticleOutputDto articleOuputDto) {
		Article articulo = new Article(articleOuputDto.getCode(), articleOuputDto.getDescription(),
				articleOuputDto.getRetailPrice());
		this.articleRepository.save(articulo);
		return articleOuputDto;
	}

	public List<ArticleOutputDto> readAll() {

		List<Article> articleList = this.articleRepository.findAll();
		List<ArticleOutputDto> articleListDto = new ArrayList<ArticleOutputDto>();
		for (Article articulo : articleList) {
			articleListDto.add(new ArticleOutputDto(articulo.getCode(), articulo.getReference(),
					articulo.getDescription(), articulo.getRetailPrice(), articulo.getStock()));
		}
		return articleListDto;

	}
}
