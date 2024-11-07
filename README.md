# MdGenerator

MdGenerator -- 接口markdown文档生成工具

MdGenerator 是根据各个xxController下声明接口的入参、返回值生成markdown文档的工具。

此工具借鉴了github上开源的markdown文档生成工具[MdKiller](https://github.com/kymotz/mdkiller)，底层构造markdown文本基于此工具实现
## 使用教程

环境：JDK1.8+

1、依赖Spring框架中的注解

2、依赖Jackson框架

3、使用示例：

然后进行如下代码操作：

```java
import com.ksyun.MdGenerator;

@AssistAnnotation
@RequestMapping(params = {"Action=Test"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.HEAD})
@Markdown
public InstancesResponse test(@Valid @RequestBody ReqCreateContainerGroupParam param) {
    return null;
}

public static void main(String[] args) throws IOException {
    MdGenerator.generateMarkdown(ContainerGroupController.class);
}
```

在`src/main/resources`目录下生成名为`api.md`文件

输出的markdown文档内容如下：

```markdown
## Action=CreateContainerGroup
### 请求方式:
POST
### Content-Type:
application/json

参数名 | 类型 | 是否必填 | 描述 | 备注
- | - | - | - | -
BillTypeId | int |  |  | 
SubnetId | Array of string |  |  | 
SecurityGroupId | string |  |  | 
Volume | Array of Object |  |  | 
ContainerGroupName | string |  |  | 
Cpu | float |  |  | 
Memory | float |  |  | 
RestartPolicy | string |  |  | 
Container | Array of Object |  |  | 
ImageRegistryCredential | Array of Object |  |  | 
KciType | string |  |  | 
MachineHostAliase | Array of Object |  |  | 
ProjectId | long |  |  | 
Count | int |  |  | 

#### MachineHostAliase 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Ip | string |  |  | 
> HostName | string |  |  | 


#### ImageRegistryCredential 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Server | string |  |  | 
> Username | string |  |  | 
> Password | string |  |  | 


#### Container 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Name | string |  |  | 
> Image | string |  |  | 
> ImageVersion | string |  |  | 
> Cpu | float |  |  | 
> Memory | float |  |  | 
> VolumeMount | Array of Object |  |  | 
> EnvironmentVar | Array of Object |  |  | 
> WorkingDir | string |  |  | 
> Command | string |  |  | 
> Arg | Array of string |  |  | 


#### EnvironmentVar 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Key | string |  |  | 
> Value | string |  |  | 


#### VolumeMount 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Name | string |  |  | 
> MountPath | string |  |  | 
> ReadOnly | boolean |  |  | 


#### Volume 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Name | string |  |  | 
> Type | string |  |  | 
> NFSVolume | Object |  |  | 
> ConfigFileVolume | Object |  |  | 
> EBSVolume | Object |  |  | 


#### EBSVolume 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> FsType | string |  |  | 
> VolumeId | string |  |  | 
> DeleteWithInstance | boolean |  |  | 
> Type | string |  |  | 
> Size | int |  |  | 
> SnapshotId | string |  |  | 


#### ConfigFileVolume 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> ConfigFileToPath | Array of Object |  |  | 


#### ConfigFileToPath 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Path | string |  |  | 
> Content | string |  |  | 


#### NFSVolume 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Server | string |  |  | 
> Path | string |  |  | 
> ReadyOnly | boolean |  |  | 


### 返回结果：
```json
{
  "id": 0,
  "version": 0,
  "createTime": "createTime_96374b9fc5b7",
  "updateTime": "updateTime_7cc89692d70a",
  "instanceId": "instanceId_b3a021679150",
  "instanceType": 0,
  "orderId": "orderId_75efb6a4abc8",
  "productId": "productId_bfd6be0266d2",
  "productGroup": 0,
  "productType": 0,
  "billType": 0,
  "region": "region_6f9b076979fc",
  "status": 0,
  "userId": 0,
  "serviceBeginTime": "serviceBeginTime_d11756bbadfb",
  "serviceEndTime": "serviceEndTime_db8bff28d8a4",
  "iamProjectId": "iamProjectId_838490f2b7e8"
}
```
## 说明
可以解析多个xxController文件，但需要将要生成文档的接口加上`@Markdown`注解，否则不会生成对应接口文档。
