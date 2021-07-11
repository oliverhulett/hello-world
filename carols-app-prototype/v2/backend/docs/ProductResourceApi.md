# ProductResourceApi

All URIs are relative to *http://cap-backend:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrUpdateUsingPOST**](ProductResourceApi.md#createOrUpdateUsingPOST) | **POST** /product/create-or-update | createOrUpdate
[**deleteByIdUsingPOST**](ProductResourceApi.md#deleteByIdUsingPOST) | **POST** /product/delete | deleteById
[**getAllUsingGET**](ProductResourceApi.md#getAllUsingGET) | **GET** /product/list | getAll
[**getByIdUsingPOST**](ProductResourceApi.md#getByIdUsingPOST) | **POST** /product/get | getById


<a name="createOrUpdateUsingPOST"></a>
# **createOrUpdateUsingPOST**
> List&lt;Product&gt; createOrUpdateUsingPOST(entities)

createOrUpdate

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.ProductResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    ProductResourceApi apiInstance = new ProductResourceApi(defaultClient);
    List<ProductEntity> entities = Arrays.asList(); // List<ProductEntity> | entities
    try {
      List<Product> result = apiInstance.createOrUpdateUsingPOST(entities);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductResourceApi#createOrUpdateUsingPOST");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entities** | [**List&lt;ProductEntity&gt;**](ProductEntity.md)| entities |

### Return type

[**List&lt;Product&gt;**](Product.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Created |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="deleteByIdUsingPOST"></a>
# **deleteByIdUsingPOST**
> deleteByIdUsingPOST(idList)

deleteById

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.ProductResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    ProductResourceApi apiInstance = new ProductResourceApi(defaultClient);
    List<String> idList = Arrays.asList(); // List<String> | idList
    try {
      apiInstance.deleteByIdUsingPOST(idList);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductResourceApi#deleteByIdUsingPOST");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **idList** | [**List&lt;String&gt;**](String.md)| idList |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Created |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getAllUsingGET"></a>
# **getAllUsingGET**
> List&lt;Product&gt; getAllUsingGET(pageNumber, size)

getAll

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.ProductResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    ProductResourceApi apiInstance = new ProductResourceApi(defaultClient);
    Integer pageNumber = 56; // Integer | pageNumber
    Integer size = 56; // Integer | size
    try {
      List<Product> result = apiInstance.getAllUsingGET(pageNumber, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductResourceApi#getAllUsingGET");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pageNumber** | **Integer**| pageNumber | [optional]
 **size** | **Integer**| size | [optional]

### Return type

[**List&lt;Product&gt;**](Product.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getByIdUsingPOST"></a>
# **getByIdUsingPOST**
> List&lt;Product&gt; getByIdUsingPOST(idList)

getById

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.ProductResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    ProductResourceApi apiInstance = new ProductResourceApi(defaultClient);
    List<String> idList = Arrays.asList(); // List<String> | idList
    try {
      List<Product> result = apiInstance.getByIdUsingPOST(idList);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductResourceApi#getByIdUsingPOST");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **idList** | [**List&lt;String&gt;**](String.md)| idList |

### Return type

[**List&lt;Product&gt;**](Product.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Created |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

