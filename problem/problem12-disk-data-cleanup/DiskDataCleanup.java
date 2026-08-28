import java.util.Arrays;

public class DiskDataCleanup {
    public static void main(String[] args) {
        DiskDataCleanup sol = new DiskDataCleanup();

        int n1 = 3;
        int m1 = 5;
        int[][] records1 = {
                {1, 1, 5},
                {2, 4, 7},
                {1, 5, 10},
                {3, 1, 10},
                {2, 1, 5},
                {1, 3, 3},
                {3, 2, 8},
                {2, 2, 8},
                {3, 4, 7}
        };

        System.out.println(Arrays.deepToString(sol.solution(n1, m1, records1)));
        // result : [[3, 1], [2, 2], [2, 4], [1, 1]]

        int n2 = 2;
        int m2 = 3;
        int[][] records2 = {
                {1, 2, 7},
                {1, 1, 7},
                {1, 3, 9},
                {2, 1, 3},
                {2, 2, 9},
                {2, 3, 1}
        };

        System.out.println(Arrays.deepToString(sol.solution(n2, m2, records2)));
        // result : [[1, 3], [2, 2], [1, 1]]
    }

    // n : 디스크 개수 m : 데이터의 종류 수 records : [디스크 번호, 저장한 데이터 번호, 최근에 참조된 일자]
    public int[][] solution(int n, int m, int[][] records) {
        boolean[] deleted = new boolean[records.length];   // 각 record가 삭제됐는지
        int[] dataCount = new int[m];     // 데이터 번호별 현재 남은 개수
        int[] diskCount = new int[n];     // 디스크별 현재 남은 데이터 개수

        for (int[] record : records) {
            int disk = record[0] - 1;
            int data = record[1] - 1;

            diskCount[disk]++;
            dataCount[data]++;
        }

        int[][] result = new int[records.length - m][2];

        for (int i = 0; i < records.length - m; i++) {
            // 현재 삭제할 후보 record의 인덱스 찾기
            int index = findDeleteCandidate(records, deleted, dataCount, diskCount);

            // 후보 record의 [디스크 번호, 데이터 번호]를 result[i]에 저장
            result[i][0] = records[index][0];
            result[i][1] = records[index][1];

            // 해당 record를 삭제 처리하고 상태 배열 갱신
            updateAfterDelete(index, records, deleted, dataCount, diskCount);
        }

        return result;
    }

    // 후보 찾기
    private int findDeleteCandidate(
            int[][] records,
            boolean[] deleted,
            int[] dataCount,
            int[] diskCount
    ) {
        int candidate = -1;

        for (int i = 0; i < records.length; i++) {
            // 이미 삭제됐으면 제외
            if (deleted[i]) {
                continue;
            }

            // 해당 데이터가 전체에 1개만 남았으면 제외
            if (dataCount[records[i][1] - 1] == 1) {
                continue;
            }

            // 첫 후보면 candidate = i
            if (candidate == -1) {
                candidate = i;
            }
            // 이후 후보는 compareDeletePriority()로 비교
            else if (compareDeletePriority(candidate, i, records, diskCount)) {
                candidate = i;
            }
        }

        return candidate;
    }

    // 삭제 우선순위 비교
    private boolean compareDeletePriority(
            int current,
            int challenger,
            int[][] records,
            int[] diskCount
    ) {
        if (records[current][2] == records[challenger][2]) {
            if (diskCount[records[current][0] - 1] == diskCount[records[challenger][0] - 1]) {
                // 저장 순서 → 작을수록 먼저 저장
                return current > challenger;
            }
            // 현재 디스크 데이터 개수 → 많을수록 우선
            else {
                return diskCount[records[current][0] - 1] < diskCount[records[challenger][0] - 1];
            }
        }
        // 최근 참조 일자 → 클수록 오래됨
        else {
            return records[current][2] < records[challenger][2];
        }
    }

    // 삭제 후 상태 갱신
    private void updateAfterDelete(
            int candidate,
            int[][] records,
            boolean[] deleted,
            int[] dataCount,
            int[] diskCount
    ) {
        deleted[candidate] = true;
        dataCount[records[candidate][1] - 1]--;
        diskCount[records[candidate][0] - 1]--;
    }
}
