/**
* 实现功能：创建一个查询结果struct 和一个服务接口service
**/
namespace java org.trump.vincent.thrift.raw.service.simplequery
struct QueryResult {
        /**
        *返回码, 1成功，0失败
        */
        1:i32 responseCode;

        /**
        *响应信息
        */
        2:string responseBody;
}
service SimpleQuery{
        /**
        * 测试查询接口，当qryCode值为1时返回"成功"的响应信息，qryCode值为其他值时返回"失败"的响应信息
        * @param name
        */
        QueryResult query(1:string name)


        void handle(1:string data)
}