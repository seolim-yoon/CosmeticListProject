## CosmeticListProject

### * Spec
#####    * Kotlin
#####    * AAC (ViewModel, Databinding, LiveData, Navigation)
#####    * Retrofit2
#####    * OkHttp
#####    * RxJava
#####    * MVVM Pattern

### * 기능
#####    * 제품의 20번째 마다 무한스크롤 기능 제공
#####    * 제품리스트의 11번째, 22번째, 31번째에 추천리스트 제공
#####    * 제품 클릭 시 상세페이지 화면

### * UI
#### 1.메인화면 (ListFragment) 
![KakaoTalk_Image_2021-07-04-20-34-43_001](https://user-images.githubusercontent.com/73940842/124383495-b66c6a00-dd07-11eb-8753-5c2ca667fe27.jpeg)

#### 2. 상세화면 (DetailFragment)
![Uploading KakaoTalk_Image_2021-07-04-20-34-43_002.jpeg…]()

#### 3. 추천리스트
![KakaoTalk_Image_2021-07-04-20-34-43_003](https://user-images.githubusercontent.com/73940842/124383536-eca9e980-dd07-11eb-966b-d92ffcc23035.jpeg)
![KakaoTalk_Image_2021-07-04-20-34-43_004](https://user-images.githubusercontent.com/73940842/124383535-ec115300-dd07-11eb-8372-030ac1148bef.jpeg)
![KakaoTalk_Image_2021-07-04-20-34-43_005](https://user-images.githubusercontent.com/73940842/124383533-e9aef900-dd07-11eb-8166-df68ebe55327.jpeg)


### * 주요 코드

#### 1. 제품 리스트 불러오기

#####  - ListFragment.kt
```
    override val viewModel: ProductViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(ProductViewModel::class.java)
    }
```
##### ViewModel을 선언해준다.

##### - ProductViewModel.kt

```
 init {
        getProductResult(1)
        getRecommendResult()
    }

    fun getProductResult(page: Int) {
        addDisposable(
            productRepository.getProductResult(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    productResponse.value = result
                    stateResult.value = StateResult.SUCCESS
                    loadPage.add(page)
                }, {
                    stateResult.value = StateResult.ERROR
                    Log.e("seolim", "error : " + it.message.toString())
                })
        )
    }

    fun getRecommendResult() {
        addDisposable(
            productRepository.getRecommendResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    recommendResponse.value = result
                    stateResult.value = StateResult.SUCCESS
                }, {
                    stateResult.value = StateResult.ERROR
                    Log.e("seolim", "error : " + it.message.toString())
                })
        )
    }
```
##### ViewModel에서 Product와 Recommend 리스트를 비동기로 받아온다.
##### 성공 시 LiveData에 넣어 ProductListAdapter에서 처리해준다.

##### - RetrofitProductAPI.kt

```
interface RetrofitProductAPI {
    @GET("public.glowday.com/test/app/product.{page}.json")
    fun getProductList(@Path("page")page: String) : Single<ProductResult>

    @GET("public.glowday.com/test/app/recommend_product.json")
    fun getRecommendList() : Single<RecommendResult>
}
```
##### URI를 통해 json파일의 내용을 각각 Single<ProductResult>/Single<RecommendResult> 형태로 받아온다.


#### 2. 제품 상세화면 호출하기

#####  - ListFragment.kt
```
   private val productListAdapter by lazy {
        ProductListAdapter {
            val bundle = Bundle()
            detailModelMapper(it)?.let { detailModel ->
                bundle.putSerializable("detailModel", detailModel)
                findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }
    }
```
##### Recyclerview의 아이템 클릭 시 bundle에 데이터를 담아 DetailFragment.kt로 보낸다.
##### -> detailModelMapper() 함수를 통해 클릭한 아이템의 자료형을 판단하여 DetailFragment.kt에 나타내기 위한 값을 보낸다.

#####  - DetailFragment.kt
```
   fun initView() {
        viewDataBinding.model = arguments?.getSerializable("detailModel") as DetailModel
        ...
    }
```
##### ListFragment.kt에서 보낸 모델을 DetailFragment.kt에서 데이터바인딩을 사용하여 ui에 값을 연결한다.

#### 3. 무한스크롤 기능

#####  - ListFragment.kt
```
    private fun initScrollView() {
        viewDataBinding.rvProductList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!viewDataBinding.rvProductList.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    if (viewModel.stateResult.value == StateResult.SUCCESS) {
                        productListAdapter.deleteLoading()
                        viewModel.getProductResult(++currentPage)
                    }
                }
            }
        })
    }
```
##### RecyclerView의 addOnScrollListener를 사용하여 가장 마지막 아이템에 도달 시 다음 데이터를 가져오도록 다.

