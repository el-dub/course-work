<script>
import {Doughnut, mixins} from "vue-chartjs";

export default {
  extends: Doughnut,
  props: ["data", "options"],
  mounted() {
    // If you want to pass options please create a local options object
    this.renderChart(this.data, {
      borderWidth: "10px",
      hoverBackgroundColor: "red",
      hoverBorderWidth: "10px"
    });
  },
  watch: {
    data() {
      this.renderChart(this.data, {
        borderWidth: "10px",
        hoverBackgroundColor: "red",
        hoverBorderWidth: "10px"
      });
    }
  }
};
</script>