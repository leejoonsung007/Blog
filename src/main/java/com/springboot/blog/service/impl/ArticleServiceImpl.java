package com.springboot.blog.service.impl;

import com.springboot.blog.dao.*;
import com.springboot.blog.dao.*;
import com.springboot.blog.dto.ArticleDto;
import com.springboot.blog.dto.ArticleWithPictureDto;
import com.springboot.blog.entity.*;
import com.springboot.blog.entity.*;
import com.springboot.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
    ArticleInfoMapper articleInfoMapper;
	@Autowired
    ArticlePictureMapper articlePictureMapper;
	@Autowired
    ArticleCategoryMapper articleCategoryMapper;
	@Autowired
    ArticleContentMapper articleContentMapper;
	@Autowired
    CategoryInfoMapper categoryInfoMapper;

	private static byte MAX_LASTEST_ARTICLE_COUNT = 5;

	//add a new article
	@Override
	public void addArticle(ArticleDto articleDto) {
		// add article info - title/summary
		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setTitle(articleDto.getTitle());
		articleInfo.setSummary(articleDto.getSummary());
		articleInfoMapper.insertSelective(articleInfo);
		// get the id of the insertion article
		Long articleId = getArticleLastestId();
		// set picture - pictureUrl/articleId
		ArticlePicture articlePicture = new ArticlePicture();
		articlePicture.setPictureUrl(articleDto.getPictureUrl());
		articlePicture.setArticleId(articleId);
		articlePictureMapper.insertSelective(articlePicture);
		// set article content  - content/articleId
		ArticleContent articleContent = new ArticleContent();
		articleContent.setContent(articleDto.getContent());
		articleContent.setArticleId(articleId);
		articleContentMapper.insertSelective(articleContent);
		// set article category - articleId/categoryId
		ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setArticleId(articleId);
		articleCategory.setCategoryId(articleDto.getCategoryId());
		articleCategoryMapper.insertSelective(articleCategory);
		// set the number of article category
		CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategory.getCategoryId());
		categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
		categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
	}

	//delete a note
	@Override
	public void deleteArticleById(Long id) {
		// get articleDto
		ArticleDto articleDto = getOneById(id);
		// delete article info
		articleInfoMapper.deleteByPrimaryKey(articleDto.getId());
		// delete article picture
		articlePictureMapper.deleteByPrimaryKey(articleDto.getArticlePictureId());
		// delete article content
		articleContentMapper.deleteByPrimaryKey(articleDto.getArticleContentId());
		// delete article category
		articleCategoryMapper.deleteByPrimaryKey(articleDto.getArticleCategoryId());
	}

	// update category data
	@Override
	public void updateArticleCategory(Long articleId, Long categoryId) {
		ArticleCategoryExample example = new ArticleCategoryExample();
		example.or().andArticleIdEqualTo(articleId);
		ArticleCategory articleCategory = articleCategoryMapper.selectByExample(example).get(0);
		// change the number of articles belong to this category
		CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategory.getCategoryId());
		categoryInfo.setNumber((byte) (categoryInfo.getNumber() - 1));
		categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
		categoryInfo = categoryInfoMapper.selectByPrimaryKey(categoryId);
		categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
		categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
		// update tbl_article_category
		articleCategory.setCategoryId(categoryId);
		articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
	}

	// update the article
	@Override
	public void updateArticle(ArticleDto articleDto) {
		// update article info
		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setId(articleDto.getId());
		articleInfo.setTitle(articleDto.getTitle());
		articleInfo.setSummary(articleDto.getSummary());
		articleInfo.setIsTop(articleDto.getTop());
		articleInfo.setTraffic(articleDto.getTraffic());
		articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
		// update article picture
		ArticlePictureExample pictureExample = new ArticlePictureExample();
		pictureExample.or().andArticleIdEqualTo(articleDto.getId());
		ArticlePicture articlePicture = articlePictureMapper.selectByExample(pictureExample).get(0);
		articlePicture.setPictureUrl(articleDto.getPictureUrl());
		articlePictureMapper.updateByPrimaryKeySelective(articlePicture);

		// update article content
		ArticleContentExample contentExample = new ArticleContentExample();
		contentExample.or().andArticleIdEqualTo(articleDto.getId());
		ArticleContent articleContent = articleContentMapper.selectByExample(contentExample).get(0);
		articleContent.setContent(articleDto.getContent());
		articleContentMapper.updateByPrimaryKeySelective(articleContent);

		// update category of article
		ArticleCategoryExample categoryExample = new ArticleCategoryExample();
		categoryExample.or().andArticleIdEqualTo(articleDto.getId());
		ArticleCategory articleCategory = articleCategoryMapper.selectByExample(categoryExample).get(0);
		articleCategory.setCategoryId(articleDto.getCategoryId());
		articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
	}

	// get a article
	@Override
	public ArticleDto getOneById(Long id) {
		ArticleDto articleDto = new ArticleDto();
		// get the article info
		ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
		articleDto.setId(articleInfo.getId());
		articleDto.setTitle(articleInfo.getTitle());
		articleDto.setSummary(articleInfo.getSummary());
		articleDto.setTop(articleInfo.getIsTop());
		articleDto.setCreateBy(articleInfo.getCreateBy());
		// increase the number of view
		articleInfo.setTraffic(articleInfo.getTraffic() + 1);
		articleDto.setTraffic(articleInfo.getTraffic() + 1);
		articleInfoMapper.updateByPrimaryKey(articleInfo);
		// get article content
		ArticleContentExample example = new ArticleContentExample();
		example.or().andArticleIdEqualTo(id);
		ArticleContent articleContent = articleContentMapper.selectByExample(example).get(0);
		articleDto.setContent(articleContent.getContent());
		articleDto.setArticleContentId(articleContent.getId());
		// get article picture
		ArticlePictureExample example1 = new ArticlePictureExample();
		example1.or().andArticleIdEqualTo(id);
		ArticlePicture articlePicture = articlePictureMapper.selectByExample(example1).get(0);
		articleDto.setPictureUrl(articlePicture.getPictureUrl());
		articleDto.setArticlePictureId(articlePicture.getId());
		// get the category
		ArticleCategoryExample example2 = new ArticleCategoryExample();
		example2.or().andArticleIdEqualTo(id);
		ArticleCategory articleCategory = articleCategoryMapper.selectByExample(example2).get(0);
		articleDto.setArticleCategoryId(articleCategory.getId());
		// get category info
		CategoryInfoExample example3 = new CategoryInfoExample();
		example3.or().andIdEqualTo(articleCategory.getCategoryId());
		CategoryInfo categoryInfo = categoryInfoMapper.selectByExample(example3).get(0);
		articleDto.setCategoryId(categoryInfo.getId());
		articleDto.setCategoryName(categoryInfo.getName());
		articleDto.setCategoryNumber(categoryInfo.getNumber());
		return articleDto;
	}

	// get all articles
	@Override
	public List<ArticleWithPictureDto> listAll() {
		// 1.get all data first
		List<ArticleWithPictureDto> articles = listAllArticleWithPicture();
		// 2.sort and put the topping article to the first place
		LinkedList<ArticleWithPictureDto> list = new LinkedList<>();
		for (int i = 0; i < articles.size(); i++) {
			if (true == articles.get(i).getTop()) {
				list.addFirst(articles.get(i));
			} else {
				list.addLast(articles.get(i));
			}
		}
		articles = new ArrayList<>(list);

		return articles;
	}

	// get all article belongs to the category
	@Override
	public List<ArticleWithPictureDto> listByCategoryId(Long id) {
		ArticleCategoryExample example = new ArticleCategoryExample();
		example.or().andCategoryIdEqualTo(id);
		List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
		List<ArticleWithPictureDto> articles = new ArrayList<>();
		for (int i = 0; i < articleCategories.size(); i++) {
			Long articleId = articleCategories.get(i).getArticleId();
			ArticleWithPictureDto articleWithPictureDto = new ArticleWithPictureDto();
			// get article Info
			ArticleInfoExample example1 = new ArticleInfoExample();
			example1.or().andIdEqualTo(articleId);
			ArticleInfo articleInfo = articleInfoMapper.selectByExample(example1).get(0);
			articleWithPictureDto.setId(articleInfo.getId());
			articleWithPictureDto.setTitle(articleInfo.getTitle());
			articleWithPictureDto.setSummary(articleInfo.getSummary());
			articleWithPictureDto.setTop(articleInfo.getIsTop());
			articleWithPictureDto.setTraffic(articleInfo.getTraffic());
			// get picture example
			ArticlePictureExample example2 = new ArticlePictureExample();
			example2.or().andArticleIdEqualTo(articleInfo.getId());
			ArticlePicture articlePicture = articlePictureMapper.selectByExample(example2).get(0);
			articleWithPictureDto.setArticlePictureId(articlePicture.getId());
			articleWithPictureDto.setPictureUrl(articlePicture.getPictureUrl());
			articles.add(articleWithPictureDto);
		}


		// re-sort, put the topping articles to the first position
		LinkedList<ArticleWithPictureDto> list = new LinkedList<>();
		for (int i = 0; i < articles.size(); i++) {
			if (true == articles.get(i).getTop()) {
				list.add(articles.get(i));
			} else {
				list.addLast(articles.get(i));
			}
		}
		articles = new ArrayList<>(list);

		return articles;
	}

	/**
	 * 获取最新的文章信息，默认为5篇
	 * 说明：listAll默认已经按照id排序了，所以我们只需要返回前五篇就可以了
	 * 注意：需要判断当前的文章是否大于5篇
	 *
	 * @return 返回五篇最新的文章数据
	 */
	@Override
	public List<ArticleWithPictureDto> listLastest() {
		// 1.先获取所有的数据
		List<ArticleWithPictureDto> articles = listAllArticleWithPicture();
		// 2.判断是否满足5个的条件
		if (articles.size() >= MAX_LASTEST_ARTICLE_COUNT) {
			// 3.大于5个则返回前五个数据
			List<ArticleWithPictureDto> tempArticles = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				tempArticles.add(articles.get(i));
			}
			return tempArticles;
		}
		// 4.不大于五个则直接返回
		return articles;
	}

	/**
	 * 返回最新插入一条数据的ID
	 *
	 * @return
	 */
	private Long getArticleLastestId() {
		ArticleInfoExample example = new ArticleInfoExample();
		example.setOrderByClause("id desc");
		return articleInfoMapper.selectByExample(example).get(0).getId();
	}

	/**
	 * 通过文章ID获取对应的文章题图信息
	 *
	 * @param id 文章ID
	 * @return 文章ID对应的文章题图信息
	 */
	@Override
	public ArticlePicture getPictureByArticleId(Long id) {
		ArticlePictureExample example = new ArticlePictureExample();
		example.or().andArticleIdEqualTo(id);
		return articlePictureMapper.selectByExample(example).get(0);
	}

	/**
	 * 获取所有的文章信息（带题图）
	 *
	 * @return
	 */
	private List<ArticleWithPictureDto> listAllArticleWithPicture() {
		ArticleInfoExample example = new ArticleInfoExample();
		example.setOrderByClause("id desc");
		// 无添加查询即返回所有
		List<ArticleInfo> articleInfos = articleInfoMapper.selectByExample(example);
		List<ArticleWithPictureDto> articles = new ArrayList<>();
		for (ArticleInfo articleInfo : articleInfos) {
			ArticleWithPictureDto articleWithPictureDto = new ArticleWithPictureDto();

			// 填充文章基础信息
			articleWithPictureDto.setId(articleInfo.getId());
			articleWithPictureDto.setTitle(articleInfo.getTitle());
			articleWithPictureDto.setSummary(articleInfo.getSummary());
			articleWithPictureDto.setTop(articleInfo.getIsTop());
			articleWithPictureDto.setTraffic(articleInfo.getTraffic());

			// 填充文章题图信息
			ArticlePictureExample example1 = new ArticlePictureExample();
			example1.or().andArticleIdEqualTo(articleInfo.getId());
			ArticlePicture articlePicture = articlePictureMapper.selectByExample(example1).get(0);
			articleWithPictureDto.setArticlePictureId(articlePicture.getId());
			articleWithPictureDto.setPictureUrl(articlePicture.getPictureUrl());
			articles.add(articleWithPictureDto);
		}
		return articles;
	}

}
