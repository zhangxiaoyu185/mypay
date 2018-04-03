package com.xiaoyu.lingdian.tool.join;

import java.util.*;

class JoinerHelper<T, K, V> {

    private List<JoinerCollector<T, K, V>> collectors = new ArrayList<>();

    public JoinerHelper<T, K, V> collect(JoinerCollector<T, K, V> collector) {

        this.collectors.add( collector );
        return this;
    }


    public Joiner<T, K, V> toJoiner(final JoinerLoader<K, V> loader, final JoinerInjector<T, K, V> injector) {

        final List<JoinerCollector<T, K, V>> finalCollectors = this.collectors;

        return new Joiner<T, K, V>() {

            @Override
            public List<K> collect(final T t) {
                Set<K> keys = new LinkedHashSet<>();

                for (JoinerCollector<T, K, V> collector : finalCollectors) {
                    keys.addAll(collector.collect(t));
                }
                return new ArrayList<>(keys);
            }

            @Override
            public Map<K, V> load(List<K> keys) {
                return loader.load(keys);
            }

            @Override
            public void inject(T t, Map<K, V> map) {
                injector.inject(t, map);
            }
        };
    }


}
