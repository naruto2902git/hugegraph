package com.baidu.hugegraph.structure;

import java.io.Serializable;

import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.structure.util.FeatureDescriptor;
import org.apache.tinkerpop.gremlin.structure.util.StringFactory;

import com.baidu.hugegraph.HugeGraph;
import com.baidu.hugegraph.configuration.ConfigSpace;
import com.baidu.hugegraph.configuration.HugeConfiguration;

/**
 * Created by jishilei on 17/3/20.
 */
public class HugeFeatures implements Graph.Features {

    protected final HugeGraph graph;
    protected final boolean supportsPersistence;
    protected final HugeGraphFeatures graphFeatures = new HugeGraphFeatures();
    protected final HugeVertexFeatures vertexFeatures = new HugeVertexFeatures();
    protected final HugeEdgeFeatures edgeFeatures = new HugeEdgeFeatures();
    protected final DataTypeFeatures dataTypeFeatures = new HugeDataTypeFeatures();

    public HugeFeatures(HugeGraph graph, boolean supportsPersistence) {
        this.graph = graph;
        this.supportsPersistence = supportsPersistence;
    }

    @Override
    public HugeGraphFeatures graph() {
        return this.graphFeatures;
    }

    @Override
    public HugeVertexFeatures vertex() {
        return this.vertexFeatures;
    }

    @Override
    public HugeEdgeFeatures edge() {
        return this.edgeFeatures;
    }

    @Override
    public String toString() {
        return StringFactory.featureString(this);
    }

    public class HugeGraphFeatures implements GraphFeatures {

        private final VariableFeatures variableFeatures = new HugeVariableFeatures();

        @Override
        public boolean supportsConcurrentAccess() {
            return false;
        }

        @Override
        public boolean supportsComputer() {
            return false;
        }

        @Override
        public boolean supportsPersistence() {
            return HugeFeatures.this.supportsPersistence;
        }

        @Override
        public VariableFeatures variables() {
            return this.variableFeatures;
        }

        @Override
        public boolean supportsTransactions() {
            return true;
        }

        @Override
        public boolean supportsThreadedTransactions() {
            return false;
        }
    }

    public class HugeElementFeatures implements ElementFeatures {

        @Override
        public boolean supportsUserSuppliedIds() {
            return false;
        }

        @Override
        public boolean supportsNumericIds() {
            return false;
        }

        @Override
        public boolean supportsStringIds() {
            return false;
        }

        @Override
        public boolean supportsUuidIds() {
            return false;
        }

        @Override
        public boolean supportsAnyIds() {
            return false;
        }

        @Override
        public boolean supportsCustomIds() {
            return false;
        }

        @Override
        public boolean willAllowId(final Object id) {
            return id instanceof Serializable;
        }
    }

    public class HugeVariableFeatures implements VariableFeatures {

        @Override
        public boolean supportsVariables() {
            return false;
        }

        @Override
        public boolean supportsBooleanValues() {
            return false;
        }

        @Override
        public boolean supportsDoubleValues() {
            return false;
        }

        @Override
        public boolean supportsFloatValues() {
            return false;
        }

        @Override
        public boolean supportsIntegerValues() {
            return false;
        }

        @Override
        public boolean supportsLongValues() {
            return false;
        }

        @Override
        public boolean supportsMapValues() {
            return false;
        }

        @Override
        public boolean supportsMixedListValues() {
            return false;
        }

        @Override
        public boolean supportsByteValues() {
            return false;
        }

        @Override
        public boolean supportsBooleanArrayValues() {
            return false;
        }

        @Override
        public boolean supportsByteArrayValues() {
            return false;
        }

        @Override
        public boolean supportsDoubleArrayValues() {
            return false;
        }

        @Override
        public boolean supportsFloatArrayValues() {
            return false;
        }

        @Override
        public boolean supportsIntegerArrayValues() {
            return false;
        }

        @Override
        public boolean supportsLongArrayValues() {
            return false;
        }

        @Override
        public boolean supportsStringArrayValues() {
            return false;
        }

        @Override
        public boolean supportsSerializableValues() {
            return false;
        }

        @Override
        public boolean supportsStringValues() {
            return false;
        }

        @Override
        public boolean supportsUniformListValues() {
            return false;
        }
    }

    public class HugeVertexPropertyFeatures implements VertexPropertyFeatures {

        @Override
        public boolean supportsAddProperty() {
            return true;
        }

        @Override
        public boolean supportsRemoveProperty() {
            return false;
        }

        @Override
        public boolean supportsMapValues() {
            return true;
        }

        @Override
        public boolean supportsMixedListValues() {
            return true;
        }

        @Override
        public boolean supportsSerializableValues() {
            return true;
        }

        @Override
        public boolean supportsUniformListValues() {
            return true;
        }

        @Override
        public boolean supportsUserSuppliedIds() {
            return false;
        }

        @Override
        public boolean supportsAnyIds() {
            return false;
        }

        @Override
        public boolean supportsCustomIds() {
            return false;
        }
    }

    public class HugeEdgePropertyFeatures implements EdgePropertyFeatures {

        @Override
        public boolean supportsMapValues() {
            return true;
        }

        @Override
        public boolean supportsMixedListValues() {
            return true;
        }

        @Override
        public boolean supportsSerializableValues() {
            return true;
        }

        @Override
        public boolean supportsUniformListValues() {
            return true;
        }

    }

    public class HugeVertexFeatures extends HugeElementFeatures implements VertexFeatures {
        private final VertexPropertyFeatures vertexPropertyFeatures = new HugeVertexPropertyFeatures();

        @Override
        public VertexPropertyFeatures properties() {
            return this.vertexPropertyFeatures;
        }

        @Override
        public boolean supportsMetaProperties() {
            return false;
        }

        @Override
        public boolean supportsMultiProperties() {
            return false;
        }

        @Override
        public VertexProperty.Cardinality getCardinality(final String key) {
            return VertexProperty.Cardinality.single;
        }

        public boolean supportsDefaultLabel() {
            return true;
        }

        public String defaultLabel() {
            Configuration conf = HugeFeatures.this.graph.configuration();
            return ((HugeConfiguration) conf).get(ConfigSpace.DEFAULT_VERTEX_LABEL);
        }
    }

    public class HugeEdgeFeatures extends HugeElementFeatures implements EdgeFeatures {

        private final EdgePropertyFeatures edgePropertyFeatures = new HugeEdgePropertyFeatures();

        @Override
        public EdgePropertyFeatures properties() {
            return this.edgePropertyFeatures;
        }
    }

    public class HugeDataTypeFeatures implements DataTypeFeatures {

        @Override
        @FeatureDescriptor(name = FEATURE_BOOLEAN_VALUES)
        public boolean supportsBooleanValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_BYTE_VALUES)
        public boolean supportsByteValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_DOUBLE_VALUES)
        public boolean supportsDoubleValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_FLOAT_VALUES)
        public boolean supportsFloatValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_INTEGER_VALUES)
        public boolean supportsIntegerValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_LONG_VALUES)
        public boolean supportsLongValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_MAP_VALUES)
        public boolean supportsMapValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_MIXED_LIST_VALUES)
        public boolean supportsMixedListValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_BOOLEAN_ARRAY_VALUES)
        public boolean supportsBooleanArrayValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_BYTE_ARRAY_VALUES)
        public boolean supportsByteArrayValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_DOUBLE_ARRAY_VALUES)
        public boolean supportsDoubleArrayValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_FLOAT_ARRAY_VALUES)
        public boolean supportsFloatArrayValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_INTEGER_ARRAY_VALUES)
        public boolean supportsIntegerArrayValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_STRING_ARRAY_VALUES)
        public boolean supportsStringArrayValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_LONG_ARRAY_VALUES)
        public boolean supportsLongArrayValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_SERIALIZABLE_VALUES)
        public boolean supportsSerializableValues() {
            return false;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_STRING_VALUES)
        public boolean supportsStringValues() {
            return true;
        }

        @Override
        @FeatureDescriptor(name = FEATURE_UNIFORM_LIST_VALUES)
        public boolean supportsUniformListValues() {
            return false;
        }
    }
}