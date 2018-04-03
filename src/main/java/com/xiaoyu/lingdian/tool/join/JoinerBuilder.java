package com.xiaoyu.lingdian.tool.join;

import org.apache.commons.collections4.CollectionUtils;
import java.util.*;

@SuppressWarnings({"unchekced", "unused"})
public class JoinerBuilder<T> {

    private JoinerBuilder() {

    }

    private JoinerQuery<T> joinerQuery;

    private List<Joiner> joiners = new ArrayList<>();

    private Map<Joiner, List> keyMap = new HashMap<>();

    private Map<Joiner, Map> valueMap = new HashMap<>();

    private Class<T> baseType;

    public Class<T> getBaseType() {
        return baseType;
    }


    public static <T> JoinerBuilder<T> newInstance(Class<T> type) {
        JoinerBuilder<T> builder = new JoinerBuilder<>();
        builder.baseType = type;
        return builder;
    }

    public static <T, K, V> JoinerHelper<T, K, V> helper(
            Class<T> t,
            Class<K> k,
            Class<V> v

    ) {
        if (t == null || k == null || v == null) {
            throw new IllegalArgumentException("argument required");
        }
        return new JoinerHelper<>();
    }

    public JoinerBuilder base(JoinerQuery<T> query) {
        if (this.joinerQuery != null) {
            throw new IllegalArgumentException("joinerQuery have already been bind");
        }
        this.joinerQuery = query;
        return this;
    }

    public JoinerBuilder join(Joiner<T, ?, ?> joiner) {
        if (joiner == null) {
            throw new IllegalArgumentException("joiner can not be null");
        }

        joiners.add(joiner);
        keyMap.put(joiner, new ArrayList());
        return this;
    }

    @SuppressWarnings("unchecked")
    public List<T> build() {

        List<T> list = joinerQuery.query();

        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        // load keys
        for (T t : list) {
            for (Joiner joiner : joiners) {
                Object key = joiner.collect(t);
                if (key != null) {
                    keyMap.get(joiner).add(key);
                }
            }
        }

        // load value
        for (Joiner joiner : joiners) {
            if (keyMap.get(joiner) == null || keyMap.get(joiner).isEmpty()) {
                continue;
            }
            Map map = joiner.load(keyMap.get(joiner));
            if (map != null) {
                valueMap.put(joiner, Collections.unmodifiableMap(map));
            }
        }

        // set value
        for (T t : list) {
            for (Joiner joiner : joiners) {
                Map value = valueMap.get(joiner);

                if (value != null ) {
                    joiner.inject(t, value);
                }
            }
        }

        return list;
    }
}
