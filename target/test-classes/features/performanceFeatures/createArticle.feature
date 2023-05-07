Feature: Performance Feature

  Background:
    * def tokenResponse = callonce read('classpath:callers/conduit/token.feature@login') {"email":#(userEmail),"password":#(userPassword)}
    * def authToken = tokenResponse.token
    Given header Authorization = 'Token ' + authToken
    Given url baseUrl
    And path 'articles'
    * def requestJson = read('classpath:datas/conduit/createArticleRequest.json')
    * def responseJson = read('classpath:datas/conduit/createArticleResponse.json')
    * def timeValidator = read('classpath:helpers/timeValidator.js')
    * def dataGenerator = Java.type('helpers.DataGenerator')

  @load
  Scenario: Create Article
    * def title = dataGenerator.getRandomTitle()
    * set requestJson.article.title = title
    * set requestJson.article.description = dataGenerator.getRandomDescription()
    * set requestJson.article.body = 'test title bal bla'
    * set requestJson.article.tagList = null
    And request requestJson
    When method POST
    And status 200
    * def articleId = response.article.slug

    Given header Authorization = 'Token ' + authToken
    When path 'articles', articleId
    And method DELETE
    And status 204

