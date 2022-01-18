<template>
  <div>
    <div class="header">
      <div class="nav">
        <a
            v-for="page in pages"
            @click="selectedLabel=page.label"
            :class="{ 'selected': page.label === selectedLabel}"
        >{{ page.label }}</a>
      </div>
      <logo class="logo"></logo>
      <div>
        <b-button class="file-button" @click="downloadFile">Download statistics</b-button>
      </div>
    </div>
    <categories-statistics v-if="selectedLabel === 'By categories'" ref="categoriesStatistics"/>
    <companies-statistics v-else-if="selectedLabel === 'By companies'" ref="companiesStatistics"/>
  </div>

</template>

<script>
import {services} from "@/components/mixins/services";
import CategoriesStatistics from "@/components/CategoriesStatistics";
import CompaniesStatistics from "@/components/CompaniesStatistics";
import PieChart from "@/components/charts/PieChart";
import Logo from "@/components/Logo";

export default {
  name: 'Main',
  components: {CompaniesStatistics, CategoriesStatistics, Logo},
  mixins: [services],
  data: function () {
    return {
      pages: [
        {label: "By categories"},
        {label: "By companies"}
      ],
      selectedLabel: "By categories"
    };
  },
  methods: {
    downloadFile() {
      if (this.selectedLabel === "By categories") {
        this.$refs.categoriesStatistics.downloadFile();
      } else if (this.selectedLabel === "By companies") {
        this.$refs.companiesStatistics.downloadFile();
      }
    }
  }
}

</script>

<style>
.nav {
  font-size: 20px;
  display: flex;
  column-gap: 20px;
  justify-content: flex-start !important;
  padding-left: 100px;
}
.nav a {
  font-weight: bold;
  color: white !important;
  font-size: 20px;
}
.nav a:hover {
  color: lavender !important;
}

.header {
  height: 80px;
  background: blueviolet;
  display: flex;
  justify-content: space-between;
  align-items: end;
  padding: 15px 100px;
}

.header>*{
  flex: 1;
  display: flex;
  justify-content: end;
}

.file-button {
  background: white !important;
  color: blueviolet !important;
  border: 0 !important;
  width: 200px;
}

.logo {
  justify-content: center;
  position: center;
}

.selected {
  border-bottom: 4px solid white;
}
</style>