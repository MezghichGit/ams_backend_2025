package com.sip.ams.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.dto.ArticleDTO;
import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.services.ArticleService;
import com.sip.ams.services.ProviderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("articles")
@CrossOrigin("*")
public class ArticleController {

	@Autowired // IOC = Inversion of controle, cad Injection dépendences
	ArticleService articleService;

	@Autowired
	ProviderService providerService;

	@GetMapping("/")
	@Operation(summary = "Récupération de tous les articles")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Succès de get All Articles"),
			@ApiResponse(responseCode = "500", description = "Problème lors de la récupération") })

	public ResponseEntity<List<Article>> getAllArticles() {
		return new ResponseEntity<>(this.articleService.getAllArticles(), HttpStatus.OK);
	}

	@PostMapping("/{idProvider}")
	@Operation(summary = "Ajout d'un nouvel acticle")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Insertion avec succès"),
			@ApiResponse(responseCode = "500", description = "Problème lors de l'insertion") })

	public ResponseEntity<Article> saveArticle(@RequestBody Article article,
			@PathVariable("idProvider") int idProvider) {

		Optional<Provider> optProvider = this.providerService.getProviderById(idProvider);

		if (optProvider.isEmpty())
			return ResponseEntity.notFound().build();
		else {

			Provider provider = optProvider.get();

			article.setProvider(provider);
			return new ResponseEntity<>(this.articleService.saveArticle(article), HttpStatus.CREATED);
		}
	}
	
	@Operation(summary = "Suppression d'un article par son id")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Si article est trouvé puis supprimé"),
			@ApiResponse(responseCode = "404", description = "Article inexistant") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Article> deleteArticleById(@PathVariable int id){
		
		Optional<Article> opt = this.articleService.getArticleById(id);
		
		if (opt.isEmpty())
			return ResponseEntity.notFound().build();  // code 404
		else {
			this.articleService.deleteArticleById(id);
			return ResponseEntity.noContent().build();  // code 204
		}
	}
	@Operation(summary = "Mise à jour d'un provider")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Si mise à jour avec succès"),
			@ApiResponse(responseCode = "404", description = "Provider inexistant") })
	
	@PutMapping("/")
	public ResponseEntity<Article> updateArticle(@RequestBody ArticleDTO articleDto){
	
		return new ResponseEntity<>(this.articleService.updateArticle(articleDto), HttpStatus.CREATED);
		
	}

}