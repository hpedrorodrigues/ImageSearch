# APIs

## Flickr

**Request**

```http
GET http://flickr.com/services/rest/?method=flickr.photos.search&text=p&per_page=1&api_key={API_KEY}&format=json
```

**Response**


```http
Status: 200 OK
Content-Type: application/json; charset=utf-8

{
  "photos": {
    "page": 1,
    "pages": 503591,
    "perpage": 1,
    "total": "503591",
    "photo": [
      {
        "id": "28509447072",
        "owner": "132012657@N05",
        "secret": "60c0317795",
        "server": "8667",
        "farm": 9,
        "title": "regram @canalviva Muito amor por #OReiDoGado! <3 Qual é o seu personagem preferido da trama? #QuintaDaNostalgia",
        "ispublic": 1,
        "isfriend": 0,
        "isfamily": 0
      }
    ]
  },
  "stat": "ok"
}
```

## Google Custom Search Engine

**Request**

```http
GET https://www.googleapis.com/customsearch/v1element?key={API_KEY}&rsz=filtered_cse&num=20&prettyPrint=false&source=gcsc&gss=.com&sig=8bdfc79787aa2b2b1ac464140255872c&start=100&searchtype=image&cx=008358958714783432655:jgkoxl4lfti&q=car&googlehost=www.google.com
```

**Response**


```http
Status: 200 OK
Content-Type: application/json; charset=utf-8

{
  "cursor": {
    "currentPageIndex": 5,
    "estimatedResultCount": "4010000000",
    "moreResultsUrl": "http://www.google.com/cse?oe=utf8&ie=utf8&source=uds&searchtype=image&q=car&start=100&cx=008358958714783432655:jgkoxl4lfti",
    "resultCount": "4,010,000,000",
    "searchResultTime": "0.42",
    "pages": [
      {
        "label": 1,
        "start": "0"
      }
    ]
  },
  "context": {
    "title": "Image Search",
    "total_results": "0",
    "facets": []
  },
  "results": [
    {
      "GsearchResultClass": "GimageSearch",
      "content": "Color Changing <b>Car</b> Paint ...",
      "contentNoFormatting": "Color Changing Car Paint ...",
      "title": "Color Changing <b>Car</b> Paint (World&#39;s First Changing <b>Car</b> Paint ) - YouTube",
      "titleNoFormatting": "Color Changing Car Paint (World's First Changing Car Paint ) - YouTube",
      "unescapedUrl": "https://i.ytimg.com/vi/74Ui-9bP9_Y/hqdefault.jpg",
      "url": "https://i.ytimg.com/vi/74Ui-9bP9_Y/hqdefault.jpg",
      "visibleUrl": "www.youtube.com",
      "originalContextUrl": "https://www.youtube.com/watch?v=74Ui-9bP9_Y",
      "height": "360",
      "width": "480",
      "tbUrl": "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSZF8MrPHoW5yEGdiuBEEZikuxUV97MhcM3bYmKBm2ENpq99cPVTd4A-3tF",
      "tbHeight": "97",
      "tbWidth": "129",
      "imageId": "ANd9GcSZF8MrPHoW5yEGdiuBEEZikuxUV97MhcM3bYmKBm2ENpq99cPVTd4A-3tF"
    }
  ]
}
```

## IMGUR

**Request**

```http
GET https://api.imgur.com/3/gallery/search?page=1&perPage=30&q=car&q_type=jpg
Headers:
  - Authorization: Client-ID {API_KEY}
```

**Response**


```http
Status: 200 OK
Content-Type: application/json; charset=utf-8

{
  "data": [
    {
      "id": "52XMdrj",
      "title": "Almost lol'ed at car dealer finance guy.",
      "description": "I almost lol'ed in the dealer finance guy's face yesterday. He put a big red stamp on the document after he doodled a bunch of nonsense on it to try and sell me unnecessary maintenance options. The stamp had a signature line that read \"customer declines benefit\".",
      "datetime": 1469535604,
      "type": "image/jpeg",
      "animated": false,
      "width": 413,
      "height": 395,
      "size": 72727,
      "views": 1179,
      "bandwidth": 85745133,
      "vote": null,
      "favorite": false,
      "nsfw": false,
      "section": "",
      "account_url": "animux",
      "account_id": 696656,
      "in_gallery": true,
      "topic": "Reaction",
      "topic_id": 23,
      "link": "http://i.imgur.com/52XMdrj.jpg",
      "is_ad": false,
      "comment_count": 16,
      "ups": 15,
      "downs": 9,
      "points": 6,
      "score": 6,
      "is_album": false
    }
  ],
  "success": true,
  "status": 200
}
```

## Duck Duck Go

**Request**

```http
GET https://api.duckduckgo.com/i.js?q=car&s=50&next=2
```

**Response**


```http
Status: 200 OK
Content-Type: application/json; charset=utf-8

{
  "ads": null,
  "next": "i.js?q=car&p=strict&s=100&u=yahoo&l=us-en",
  "query": "car",
  "results": [
    {
      "source": "Yahoo",
      "width": 1920,
      "thumbnail": "https://tse2.mm.bing.net/th?id=OIP.M3565152507258cbf2f3f22ea3f4fb9adH0&pid=15.1",
      "url": "http://www.hdwallpapers.in/new_bmw_z4_2011_car-wallpapers.html",
      "title": "New BMW Z4 2011 Car Wallpapers | HD Wallpapers",
      "height": 1200,
      "image": "http://hdwallpapers.in/walls/new_bmw_z4_2011_car-wide.jpg"
    }
  ]
}
```

## Bing

**Request**

```http
GET https://api.cognitive.microsoft.com/bing/v5.0/images/search?q=cats&count=10&offset=0&mkt=en-us&safeSearch=Moderate
Headers:
  - Ocp-Apim-Subscription-Key: {API_KEY}
```

**Response**


```http
Status: 200 OK
Content-Type: application/json; charset=utf-8

{
  "_type": "Images",
  "instrumentation": {
    "pageLoadPingUrl": "https://www.bingapis.com/api/ping/pageload?IG=B73E5F2CD3D44B549673BBAA2365503F&CID=2231E2349044689A22ECEB54918469DD&Type=Event.CPT&DATA=0"
  },
  "readLink": "https://api.cognitive.microsoft.com/api/v5/images/search?q=cats",
  "webSearchUrl": "https://www.bing.com/cr?IG=B73E5F2CD3D44B549673BBAA2365503F&CID=2231E2349044689A22ECEB54918469DD&rd=1&h=2PHX-FVpdYHh0O2OR2bVnJ_5wucEhtoE-5mrVS58bcQ&v=1&r=https%3a%2f%2fwww.bing.com%2fimages%2fsearch%3fq%3dcats%26FORM%3dOIIARP&p=DevEx,5116.1",
  "totalEstimatedMatches": 1000,
  "value": [
    {
      "name": "True Wild Life",
      "webSearchUrl": "https://www.bing.com/cr?IG=B73E5F2CD3D44B549673BBAA2365503F&CID=2231E2349044689A22ECEB54918469DD&rd=1&h=enCpjmGGqGWurKFTBir-TuJW863HOeC7Ur5bEOadiLg&v=1&r=https%3a%2f%2fwww.bing.com%2fimages%2fsearch%3fview%3ddetailv2%26FORM%3dOIIRPO%26q%3dcats%26id%3d14CE08301947D0E85F0FA2BC6D8C9230BCE345EF%26simid%3d608017982662969227&p=DevEx,5006.1",
      "thumbnailUrl": "https://tse4.mm.bing.net/th?id=OIP.M2a50172cce1e37480b2df61561532aa4H0&pid=Api",
      "datePublished": "2011-02-18T12:00:00",
      "contentUrl": "http://2.bp.blogspot.com/-WtdFq_e6eKo/TV5W5s-hS-I/AAAAAAAAAvM/gmCUYOx3bX8/s1600/Animals_Cats_Small_cat_005241_.jpg",
      "hostPageUrl": "http://www.bing.com/cr?IG=B73E5F2CD3D44B549673BBAA2365503F&CID=2231E2349044689A22ECEB54918469DD&rd=1&h=0_q9dolAf9XX5UfgQwrnAh7W1aihqedkaTsOfJWPhiQ&v=1&r=http%3a%2f%2ftrue-wildlife.blogspot.com%2f2011%2f02%2fcat.html&p=DevEx,5007.1",
      "contentSize": "267846 B",
      "encodingFormat": "jpeg",
      "hostPageDisplayUrl": "true-wildlife.blogspot.com/2011/02/cat.html",
      "width": 1152,
      "height": 864,
      "thumbnail": {
        "width": 300,
        "height": 225
      },
      "imageInsightsToken": "ccid_KlAXLM4e*mid_14CE08301947D0E85F0FA2BC6D8C9230BCE345EF*simid_608017982662969227",
      "insightsSourcesSummary": {
        "shoppingSourcesCount": 3,
        "recipeSourcesCount": 0
      },
      "imageId": "14CE08301947D0E85F0FA2BC6D8C9230BCE345EF",
      "accentColor": "B91F12"
    }
  ],
  "queryExpansions": [
    {
      "text": "Grumpy Cat",
      "displayText": "Grumpy",
      "webSearchUrl": "https://www.bing.com/cr?IG=B73E5F2CD3D44B549673BBAA2365503F&CID=2231E2349044689A22ECEB54918469DD&rd=1&h=7OwLclF0MQlVhnfMUs8xnTO7Uo7JHUOzTD7eHQwWzP4&v=1&r=https%3a%2f%2fwww.bing.com%2fimages%2fsearch%3fq%3dGrumpy%2bCat%26tq%3d%257b%2522pq%2522%253a%2522cats%2522%252c%2522qs%2522%253a%255b%257b%2522cv%2522%253a%2522cats%2522%252c%2522pv%2522%253a%2522cats%2522%252c%2522hps%2522%253atrue%252c%2522iqp%2522%253afalse%257d%252c%257b%2522cv%2522%253a%2522Grumpy%2522%252c%2522pv%2522%253a%2522%2522%252c%2522hps%2522%253afalse%252c%2522iqp%2522%253atrue%257d%255d%257d%26FORM%3dIRPATC&p=DevEx,5120.1",
      "searchLink": "https://api.cognitive.microsoft.com/api/v5/images/search?q=Grumpy+Cat&tq=%7b%22pq%22%3a%22cats%22%2c%22qs%22%3a%5b%7b%22cv%22%3a%22cats%22%2c%22pv%22%3a%22cats%22%2c%22hps%22%3atrue%2c%22iqp%22%3afalse%7d%2c%7b%22cv%22%3a%22Grumpy%22%2c%22pv%22%3a%22%22%2c%22hps%22%3afalse%2c%22iqp%22%3atrue%7d%5d%7d",
      "thumbnail": {
        "thumbnailUrl": "https://tse3.mm.bing.net/th?q=Grumpy+Cat&pid=Api&mkt=en-US&adlt=moderate&t=1"
      }
    }
  ],
  "nextOffsetAddCount": 19,
  "pivotSuggestions": [
    {
      "pivot": "cats",
      "suggestions": [
        {
          "text": "Felidae",
          "displayText": "Felidae",
          "webSearchUrl": "https://www.bing.com/cr?IG=B73E5F2CD3D44B549673BBAA2365503F&CID=2231E2349044689A22ECEB54918469DD&rd=1&h=Ka54BH0s03VuPnX7F66YskEsMVO20LnxtyxjhggahKc&v=1&r=https%3a%2f%2fwww.bing.com%2fimages%2fsearch%3fq%3dFelidae%26tq%3d%257b%2522pq%2522%253a%2522cats%2522%252c%2522qs%2522%253a%255b%257b%2522cv%2522%253a%2522cats%2522%252c%2522pv%2522%253a%2522cats%2522%252c%2522hps%2522%253atrue%252c%2522iqp%2522%253afalse%257d%252c%257b%2522cv%2522%253a%2522Felidae%2522%252c%2522pv%2522%253a%2522%2522%252c%2522hps%2522%253afalse%252c%2522iqp%2522%253atrue%257d%255d%257d%26FORM%3dIRQBPS&p=DevEx,5205.1",
          "searchLink": "https://api.cognitive.microsoft.com/api/v5/images/search?q=Felidae&tq=%7b%22pq%22%3a%22cats%22%2c%22qs%22%3a%5b%7b%22cv%22%3a%22cats%22%2c%22pv%22%3a%22cats%22%2c%22hps%22%3atrue%2c%22iqp%22%3afalse%7d%2c%7b%22cv%22%3a%22Felidae%22%2c%22pv%22%3a%22%22%2c%22hps%22%3afalse%2c%22iqp%22%3atrue%7d%5d%7d",
          "thumbnail": {
            "thumbnailUrl": "https://tse3.mm.bing.net/th?q=Felidae&pid=Api&mkt=en-US&adlt=moderate&t=1"
          }
        }
      ]
    }
  ],
  "displayShoppingSourcesBadges": false,
  "displayRecipeSourcesBadges": true
}
```

## Pixabay

**Request**

```http
GET https://pixabay.com/api/?key={API_KEY}&q=carro&image_type=photo&page=1&per_page=5
```

**Response**


```http
Status: 200 OK
Content-Type: application/json; charset=utf-8

{
  "totalHits": 500,
  "hits": [
    {
      "previewHeight": 100,
      "likes": 17,
      "favorites": 10,
      "tags": "cart, wheel, agriculture",
      "webformatHeight": 428,
      "views": 4618,
      "webformatWidth": 640,
      "previewWidth": 150,
      "comments": 1,
      "downloads": 2902,
      "pageURL": "https://pixabay.com/en/cart-wheel-agriculture-farm-1049327/",
      "previewURL": "https://pixabay.com/static/uploads/photo/2015/11/18/15/38/cart-1049327_150.jpg",
      "webformatURL": "https://pixabay.com/get/e835b5062bf6063ed95c4518b74f4696e573e1d704b015479cf8c57aa1e8b4_640.jpg",
      "imageWidth": 3872,
      "user_id": 483877,
      "user": "jackmac34",
      "type": "photo",
      "id": 1049327,
      "userImageURL": "https://pixabay.com/static/uploads/user/2014/10/03/19-00-06-229_250x250.jpg",
      "imageHeight": 2592
    }
  ],
  "total": 1029
}
```