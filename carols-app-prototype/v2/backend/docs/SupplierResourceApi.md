# SupplierResourceApi

All URIs are relative to *http://cap-backend:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrUpdateUsingPOST1**](SupplierResourceApi.md#createOrUpdateUsingPOST1) | **POST** /supplier/create-or-update | createOrUpdate
[**deleteByIdUsingPOST1**](SupplierResourceApi.md#deleteByIdUsingPOST1) | **POST** /supplier/delete | deleteById
[**getAllUsingGET1**](SupplierResourceApi.md#getAllUsingGET1) | **GET** /supplier/list | getAll
[**getByIdUsingPOST1**](SupplierResourceApi.md#getByIdUsingPOST1) | **POST** /supplier/get | getById


<a name="createOrUpdateUsingPOST1"></a>
# **createOrUpdateUsingPOST1**
> List&lt;Supplier&gt; createOrUpdateUsingPOST1(entities)

createOrUpdate

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.SupplierResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    SupplierResourceApi apiInstance = new SupplierResourceApi(defaultClient);
    List<SupplierEntity> entities = Arrays.asList(); // List<SupplierEntity> | entities
    try {
      List<Supplier> result = apiInstance.createOrUpdateUsingPOST1(entities);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SupplierResourceApi#createOrUpdateUsingPOST1");
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
 **entities** | [**List&lt;SupplierEntity&gt;**](SupplierEntity.md)| entities |

### Return type

[**List&lt;Supplier&gt;**](Supplier.md)

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

<a name="deleteByIdUsingPOST1"></a>
# **deleteByIdUsingPOST1**
> deleteByIdUsingPOST1(idList)

deleteById

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.SupplierResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    SupplierResourceApi apiInstance = new SupplierResourceApi(defaultClient);
    List<String> idList = Arrays.asList(); // List<String> | idList
    try {
      apiInstance.deleteByIdUsingPOST1(idList);
    } catch (ApiException e) {
      System.err.println("Exception when calling SupplierResourceApi#deleteByIdUsingPOST1");
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

<a name="getAllUsingGET1"></a>
# **getAllUsingGET1**
> List&lt;Supplier&gt; getAllUsingGET1(pageNumber, size)

getAll

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.SupplierResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    SupplierResourceApi apiInstance = new SupplierResourceApi(defaultClient);
    Integer pageNumber = 56; // Integer | pageNumber
    Integer size = 56; // Integer | size
    try {
      List<Supplier> result = apiInstance.getAllUsingGET1(pageNumber, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SupplierResourceApi#getAllUsingGET1");
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

[**List&lt;Supplier&gt;**](Supplier.md)

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

<a name="getByIdUsingPOST1"></a>
# **getByIdUsingPOST1**
> List&lt;Supplier&gt; getByIdUsingPOST1(idList)

getById

### Example
```java
// Import classes:
import com.cap.backend.ApiClient;
import com.cap.backend.ApiException;
import com.cap.backend.Configuration;
import com.cap.backend.models.*;
import com.cap.backend.api.SupplierResourceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://cap-backend:8080");

    SupplierResourceApi apiInstance = new SupplierResourceApi(defaultClient);
    List<String> idList = Arrays.asList(); // List<String> | idList
    try {
      List<Supplier> result = apiInstance.getByIdUsingPOST1(idList);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SupplierResourceApi#getByIdUsingPOST1");
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

[**List&lt;Supplier&gt;**](Supplier.md)

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

