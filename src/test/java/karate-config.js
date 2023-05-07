function fn() {
  var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);

  if (!env) {
    env = 'dev'; // a custom 'intelligent' default
  }
  var config = { // base config JSON
   baseUrl: 'https://api.realworld.io/api/'
  };

  if (env == 'dev') {
  config.userEmail = "tdemailtestdata@gmail.com",
  config.userPassword = "Trendyol123!"

  } else if (env == 'e2e') {

  }

 // var accessToken = karate.callSingle('classpath:features/conduit/postRequest5.feature@login',config).token;
 // karate.configure('headers', {Authorization: 'Token ' + accessToken,
 //                              'Content-Type': 'application/json'})

  // don't waste time waiting for a connection or if servers don't respond within 5 seconds
  //karate.configure('connectTimeout', 5000);
  //karate.configure('readTimeout', 5000);
  return config;
}