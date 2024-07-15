# Shop Application
## Project
### Version
- spring boot: 3.3.1
- gradle: 8.8
- java: 17

## APIs
swagger api 문서 : http://localhost:8080/swagger-ui/index.html#/

### 구현1 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회
- http://localhost:8080/swagger-ui/index.html#/item-controller/getItemsWithLowestPrice
- Method : GET items/category/lowest

### 구현2 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회
- http://localhost:8080/swagger-ui/index.html#/item-controller/getItemsLowestTotal
- Method : GET http://localhost:8080/items/brand

### 구현3 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회
- http://localhost:8080/swagger-ui/index.html#/item-controller/getItemsWithLowestAndHighestPrice
- Method : GET http://localhost:8080/items/category
- Parameter : categoryName (String)

### 구현4-1 브랜드 및 상품 추가
- http://localhost:8080/swagger-ui/index.html#/item-controller/addItem
- Method : POST
- Body (필수 파라미터)
  - {
        "categoryId": 1, (1~8)
        "brandName": "J",
        "price": 100000
    }

### 구현4-2 브랜드 및 상품 수정
- http://localhost:8080/swagger-ui/index.html#/item-controller/modifyItem
- Method : PUT
- Body (필수 파라미터)
  - {
        "itemId": 73,
        "categoryId": 2, (or/both brandId)
        "price": 26000,
    }

### 구현4-3 브랜드 및 상품 삭제
- http://localhost:8080/swagger-ui/index.html#/item-controller/removeItem
- Method : DELETE
- Parameter : itemId (Long)

## Database
- 로컬 주소 : http://localhost:8080/h2-console/
- ID, PW 없음
- jdbc url : jdbc:h2:~/test