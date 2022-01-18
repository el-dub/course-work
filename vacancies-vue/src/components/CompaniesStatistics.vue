<template>
  <div class="wrapper">
    <div class="charts-section">
      <div class="filters">
        <label class="typo__label">Select time bounds</label>
        <date-picker class="date-picker" v-model="time" range></date-picker>
        <label class="typo__label">Select categories</label>
        <multiselect
            v-model="categoryFilter"
            :options="categoryOptions"
            :multiple="true"
            :close-on-select="false"
            :clear-on-select="false"
            :preserve-search="true"
            group-values="categories"
            group-label="group"
            :group-select="true"
            placeholder="Pick some"
            label="name"
            track-by="id"
        >
          <template slot="tag" slot-scope="props"><span></span></template>
          <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span></template>
        </multiselect>
        <label class="typo__label">Select levels</label>
        <multiselect
            v-model="levelFilter"
            :options="levelOptions"
            :multiple="true"
            :close-on-select="false"
            :clear-on-select="false"
            :preserve-search="true"
            group-values="levels"
            group-label="group"
            :group-select="true"
            placeholder="Pick some"
            label="name"
            track-by="id"
        >
          <template slot="tag" slot-scope="props"><span></span></template>
          <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span></template>
        </multiselect>
        <label class="typo__label">Select locations</label>
        <multiselect
            v-model="locationFilter"
            :options="locationOptions"
            :multiple="true"
            :close-on-select="false"
            :clear-on-select="false"
            :preserve-search="true"
            group-values="locations"
            group-label="group"
            :group-select="true"
            placeholder="Pick some"
            label="name"
            track-by="id"
        >
          <template slot="tag" slot-scope="props"><span></span></template>
          <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span></template>
        </multiselect>
        <b-button class="stat-bt" @click="loadStatistics">Show statistics</b-button>
      </div>
      <pie-chart class="chart" :options="options" :data="chartData" v-if="vacanciesData.items.length > 0"/>
      <div v-else class="no-data-message">
        {{ noDataMessage }}
      </div>
      <div class="table-section">
        <b-table
            v-if="vacanciesData.items.length > 0"
            id="table"
            class="table"
            striped
            :items="tableData.items"
            :fields="tableData.fields"
            label-sort-asc=""
            label-sort-desc=""
            :per-page="perPage"
            :current-page="currentPage"
        ></b-table>
        <b-pagination
            v-if="vacanciesData.items.length > 0"
            class="pagination"
            v-model="currentPage"
            :total-rows="totalRows"
            :per-page="perPage"
            aria-controls="table"
        ></b-pagination>
      </div>
    </div>
    <div class="charts-section" v-if="vacanciesData.items.length > 0">
      <bar-chart class="chart" :options="options" :data="chartData"/>
    </div>
  </div>
</template>
<script>
import PieChart from "@/components/charts/PieChart";
import BarChart from "@/components/charts/BarChart";
import {constants} from "@/components/mixins/constants";
import {services} from "@/components/mixins/services";
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
import Multiselect from 'vue-multiselect'

const options = {
  responsive: true,
  maintainAspectRatio: false,
  animation: {
    animateRotate: false
  }
}

export default {
  components: {PieChart, DatePicker, Multiselect, BarChart},
  mixins: [constants, services],
  data: function () {
    return {
      options,
      vacanciesData: {
        sum: 0,
        items: []
      },
      time: [new Date("2021-09-01T00:00:00"), new Date()],
      MySelectedValues: [],
      categories: [],
      levels: [],
      locations: [],
      categoryOptions: [{
        group: 'All',
        categories: []
      }],
      levelOptions: [{
        group: 'All',
        levels: []
      }],
      locationOptions: [{
        group: 'All',
        locations: []
      }],
      categoryFilter: [],
      levelFilter: [],
      locationFilter: [],
      currentPage: 1,
      perPage: 10,
      noDataMessage: "Click 'Show statistics'"
    }
  },
  computed: {
    labels: function () {
      return this.vacanciesForChart.map(vacancy => vacancy.name);
    },
    numbers: function () {
      return this.vacanciesForChart.map(vacancy => vacancy.number);
    },
    chartColors: function () {
      return this.COLORS.slice(0, this.vacanciesForChart.length);
    },
    chartData: function () {
      return {
        hoverBackgroundColor: "red",
        hoverBorderWidth: 10,
        labels: this.labels,
        datasets: [
          {
            backgroundColor: this.chartColors,
            data: this.numbers
          }
        ]
      };
    },
    dateFrom: function () {
      return this.time[0].toISOString();
    },
    dateTo: function () {
      return this.time[1].toISOString();
    },
    filter: function () {
      return {
        dateFrom: this.dateFrom,
        dateTo: this.dateTo,
        categoryIds: this.categoryFilter.map(category => category.id),
        levelIds: this.levelFilter.map(level => level.id),
        locationIds: this.locationFilter.map(location => location.id)
      }
    },
    vacanciesForChart: function () {
      return this.getChartData(this.vacanciesData);
    },
    tableData: function () {
      return {
        fields: [
          {
            key: 'company',
            sortable: true
          },
          {
            key: 'number',
            sortable: true
          }
        ],
        items: this.vacanciesData.items.map(vacancy => {
          vacancy.company = vacancy.name;
          return vacancy;
        })
      }
    },
    totalRows: function() {
      return this.vacanciesData.items.length;
    }
  },
  async created() {
    await this.loadCategories();
    await this.loadLevels();
    await this.loadLocations();
    // await this.loadStatistics();
  },
  methods: {
    async loadStatistics() {
      this.noDataMessage = "No data for this filters.";
      const statisticsResponse = await this.getStatisticsByCompanies(this.filter);
      this.vacanciesData = statisticsResponse.data;
    },
    async loadCategories() {
      const categoriesResponse = await this.getCategories();
      this.categories = categoriesResponse.data;
      this.categoryOptions[0].categories = this.categories;
      this.categoryFilter = this.categories;
    },
    async loadLevels() {
      const levelsResponse = await this.getLevels();
      this.levels = levelsResponse.data;
      this.levelOptions[0].levels = this.levels;
      this.levelFilter = this.levels;
    },
    async loadLocations() {
      const locationsResponse = await this.getLocations();
      this.locations = locationsResponse.data;
      this.locationOptions[0].locations = this.locations;
      this.locationFilter = this.locations;
    },
    downloadFile() {
      if (this.vacanciesData.items.length === 0) {
        this.noDataMessage = "No data. File cannot be downloaded";
      } else {
        let csv = 'Category,Number\n';
        this.vacanciesData.items.forEach((item) => {
          csv += item.name + ',' + item.number;
          csv += "\n";
        });
        const anchor = document.createElement('a');
        anchor.href = 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv);
        anchor.target = '_blank';
        anchor.download = 'statisticsByCategories.csv';
        anchor.click();
      }
    }
  },
  // watch: {
  //   async time() {
  //     await this.loadStatistics();
  //   },
  //   async companyFilter() {
  //     await this.loadStatistics();
  //   },
  //   async levelFilter() {
  //     await this.loadStatistics();
  //   },
  //   async locationFilter() {
  //     await this.loadStatistics();
  //   },
  // }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>
.charts-section {
  height: 500px;
  display: flex;
  justify-content: space-between;
  column-gap: 30px;
}

.charts-section>* {
  flex: 1;
}

.chart {
  max-height: 400px;
}

.wrapper {
  display: flex;
  flex-direction: column;
  padding: 25px 100px;
}

.table-section {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.filters {
  display: flex;
  flex-direction: column;
  row-gap: 20px;
  padding: 0 10px;
}

#table {
  min-height: 452px;
}

.date-picker {
  width: 100%;
}

</style>
