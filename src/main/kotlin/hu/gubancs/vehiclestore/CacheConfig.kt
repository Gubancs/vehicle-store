package hu.gubancs.vehiclestore

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.jcache.JCacheCacheManager
import org.springframework.cache.jcache.config.JCacheConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
class CacheConfig : JCacheConfigurer {
    @Bean
    override fun cacheManager(): CacheManager {
        return JCacheCacheManager()
    }
}
