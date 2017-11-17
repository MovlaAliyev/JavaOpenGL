<?php
class MyCache {

    private $path;
    
    private $expire_time;
    private $file_extention;
    
    function __construct() {
        $this->setPath('cache/');
        $this->setFileExtention('.cache');
        $this->setExpireTime(100);
    }
    
    public function isCached($cacheName){
        $hashedCacheName = $this->hashCacheName($cacheName);
        $exist = (file_exists($this->getPath().$hashedCacheName.$this->getFileExtention())) 
                ? filemtime($this->getPath().$hashedCacheName.$this->getFileExtention()) : 0;
        
        $val = ((time() - $this->getExpireTime()) < $exist);
        return $val;
    }
    
    public function addCache($cacheName, $data){
        $hashedCacheName = $this->hashCacheName($cacheName);
        $storeData = array(
                $cacheName => serialize($data),
        );
        $cacheData = json_encode($storeData);
        $fp        = fopen($this->getPath().$hashedCacheName.$this->getFileExtention(), 'w');
        fwrite($fp, $cacheData);
        fclose($fp);
    }
    
    public function getCachedData($cacheName, $key){
        $hashedCacheName = $this->hashCacheName($cacheName);
        $cachedData = json_decode(file_get_contents($this->getPath().$hashedCacheName.$this->getFileExtention()), true);
        return unserialize($cachedData[$key]);
    }
    
    function getPath() {
        return $this->path;
    }

    function setPath($path) {
        $this->path = $path;
    }

    function getExpireTime() {
        return $this->expire_time;
    }

    function setExpireTime($expire_time) {
        $this->expire_time = $expire_time;
    }
    
    
    
    function setFileExtention($file_extention){
        $this->file_extention = $file_extention;
    }
    
    function getFileExtention(){
        return $this->file_extention;
    }
    
    private function hashCacheName($cacheName){
        return sha1($cacheName);
    }
}

