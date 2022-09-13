<template>
	<ContentField>
		<table class="table table-striped table-hover" style="text-align: center;">
			<thead class="">
				<th>玩家A</th>
				<th>玩家B</th>
				<th>对战结果</th>
				<th>对战时间</th>
				<th>操作</th>
			</thead>
			<tbody>
				<tr v-for="record in records" :key="record.record.id">
					<td>
						<img :src="record.a_photo" class="record-user-photo">
						&nbsp;
						<span class="record-user-username">{{ record.a_username }}</span>
					</td>
					<td>
						<img :src="record.b_photo" class="record-user-photo">
						&nbsp;
						<span class="record-user-username">{{ record.b_username }}</span>
					</td>
					<td>
						{{ record.result }}
					</td>
					<td>
						{{ record.record.createTime }}
					</td>
					<td>
						<button @click="open_record_content(record.record.id)" type="button" class="btn btn-secondary">
							查看录像
						</button>
					</td>
				</tr>
			</tbody>
		</table>
		<nav aria-label="...">
			<ul class="pagination" style="float: right;">
				<li class="page-item" @click="click_page(-2)">
					<a class="page-link" href="#">上一页</a>
				</li>
				<li v-for="page in pages" :key="page.number" :class="'page-item ' + page.is_active"
					@click="click_page(page.number)">
					<a class="page-link" href="#">
						{{ page.number }}
					</a>
				</li>

				<li class="page-item" @click="click_page(-1)">
					<a class="page-link" href="#">下一页</a>
				</li>
			</ul>
		</nav>
	</ContentField>
</template>

<script>
import ContentField from "@/components/ContentField";
import { useStore } from "vuex";
import $ from 'jquery';
import { ref } from "vue";
import router from "../../router";

export default {
	name: "RecordIndexView",
	components: { ContentField },
	setup() {
		const store = useStore();
		let records = ref([]);
		let current_count = 0;
		let current_page = 1;
		let pages = ref([]);

		const click_page = page => {
			if (page === -2) page = current_page - 1;
			else if (page === -1) page = current_page + 1;
			let max_pages = parseInt(Math.ceil(current_count / 10));

			if (page >= 1 && page <= max_pages) {
				pull_page(page);
			}
		}

		const update_pages = () => {
			let max_pages = parseInt(Math.ceil(current_count / 10));
			let new_pages = [];
			for (let i = current_page - 2; i <= current_page + 2; i++) {
				if (i >= 1 && i <= max_pages) {
					new_pages.push({
						number: i,
						is_active: i == current_page ? "active" : ""
					});
				}
			}
			pages.value = new_pages;
		}

		const pull_page = page => {
			current_page = page;
			$.ajax({
				type: "GET",
				url: "https://pipibr.com/api/record/getlist/",
				data: {
					page,
				},
				headers: {
					Authorization: "Bearer " + store.state.user.token,
				},
				success(resp) {
					records.value = resp.records;
					current_count = resp.record_count;
					update_pages();
				},
				error(resp) {
					console.log(resp);
				}
			});
		}

		const stringTo2D = (map) => {
			let g = [];
			for (let i = 0, k = 0; i < 13; i++) {
				let line = [];
				for (let j = 0; j < 14; j++, k++) {
					if (map[k] === '0') line.push(0);
					else line.push(1);
				}
				g.push(line);
			}
			return g;
		}

		const open_record_content = (recordId) => {
			for (const record of records.value) {
				if (record.record.id == recordId) {
					localStorage.setItem("current_page", current_page);
					store.commit("updateIsRecord", true);
					store.commit("updateGame", {
						map: stringTo2D(record.record.map),
						a_id: record.record.aId,
						a_sx: record.record.aSx,
						a_sy: record.record.aSy,
						b_id: record.record.aId,
						b_sx: record.record.bSx,
						b_sy: record.record.bSy,
					});
					store.commit("updateSteps", {
						a_steps: record.record.aSteps,
						b_steps: record.record.bSteps,
					});
					store.commit("updateRecordLoser", record.record.loser);
					router.push({
						name: "record_content",
						params: {
							recordId
						}
					})
					break;
				}
			}
		}
		if (localStorage.getItem("current_page") === null)
			pull_page(current_page);
		else
			pull_page(localStorage.getItem("current_page"));
		return {
			records,
			current_count,
			open_record_content,
			pages,
			click_page
		}
	}
}
</script>

<style scoped>
.record-user-photo {
	width: 4vh;
	border-radius: 50%;
}
</style>