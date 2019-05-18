package com.lynsite.blog.sentinel.config;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.sentinel.SentinelProperties;
import org.springframework.cloud.alibaba.sentinel.datasource.config.NacosDataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/5/7 9:19
 * @Version: 1.0
 */
@Configuration
public class DataSourceInitFunc {

    Logger logger = LoggerFactory.getLogger(DataSourceInitFunc.class);

    @Autowired
    private SentinelProperties sentinelProperties;

    @Bean
    public DataSourceInitFunc init() throws Exception {

        logger.info("[NacosSource初始化,从Nacos中获取规则]");

        sentinelProperties.getDatasource().entrySet().stream().filter(map -> {
            return map.getValue().getNacos() != null;
        }).forEach(map -> {
            NacosDataSourceProperties nacos = map.getValue().getNacos();

            // 限流规则-->flow
            if(nacos.getDataId().contains("flow")){
                ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                        }));
                FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
                logger.info("Nacos中获取: {}", "限流规则");
            }


            // 降级规则-->degrade
            if(nacos.getDataId().contains("degrade")){
                ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                        }));
                DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
                logger.info("Nacos中获取: {}", "降级规则");
            }

            // 热点规则-->param
            if(nacos.getDataId().contains("param")){
                ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                        }));
                ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());
                logger.info("Nacos中获取: {}", "热点规则");
            }

            // 系统规则-->system
            if(nacos.getDataId().contains("system")){
                ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {
                        }));
                SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
                logger.info("Nacos中获取: {}", "系统规则");
            }

            // 授权规则-->authority
            if(nacos.getDataId().contains("authority")){
                ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {
                        }));
                AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());
                logger.info("Nacos中获取: {}", "授权规则");
            }

            //集群流控-->cluster
            if(nacos.getDataId().contains("flow")){
                final String FLOW_POSTFIX = "-flow-rules";
                ClusterFlowRuleManager.setPropertySupplier(namespace -> {
                    ReadableDataSource<String, List<FlowRule>> ds = new NacosDataSource<>(nacos.getServerAddr(),
                            nacos.getGroupId(), namespace+FLOW_POSTFIX,
                            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                            }));
                    return ds.getProperty();
                });
                logger.info("Nacos中获取: {}", "集群流控");
            }

//            String clientConfigDataId = "cluster-client-config";
//            // 初始化一个配置ClusterClientConfig的 Nacos 数据源
//            ReadableDataSource<String, ClusterClientConfig> ds = new NacosDataSource<>(nacos.getServerAddr(),
//                    nacos.getGroupId(), clientConfigDataId,
//                    source -> JSON.parseObject(source, new TypeReference<ClusterClientConfig>() {}));
//            ClusterClientConfigManager.register2Property(ds.getProperty());
        });
        return new DataSourceInitFunc();
    }
}
