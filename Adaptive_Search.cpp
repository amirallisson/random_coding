/* *****************************************************************************
 * Amir Mokhammed-Ali
 *
 * Решение задачи №647 - Адаптивный поиск
 * Ссылка на задачу: http://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=20&id_topic=46&id_problem=1178
 * 
 * В решении реализуется дерево отрезков, с операциями изменения элемента и подсчета суммы на промежутке, с помощью
 * которых нам удается определить количество просмотренных записей для каждого поступающего запроса
 *
 * Временная сложность алгоритма: (N+M)log(N+M)
 * Память: 4*N
 **************************************************************************** */
 
 
 #include<bits/stdc++.h>
using namespace std;
 
 
#define pb push_back
#define fi first
#define se second
#define pii pair<int, int>
#define vi vector<int>
typedef long long ll;
const int N  = 1e5;
 
int tree[4*N]{0}, cur[4*N]{0};
int n, m;
int s;
void build(){
    for(int i = s-1; i>0; --i) tree[i] = tree[i<<1] + tree[i<<1|1];
}
 
void update(int v, int val){
    for(v+=s; v>0; v>>=1) tree[v] += val;
}
 
int query(int l, int r){
    int sum = 0;
    for(l+=s, r+=s; l<r; l>>=1, r>>=1){
        if(l&1) sum+=tree[l++];
        if(r&1) sum+=tree[--r];
    }
    return sum;
}
 
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    //freopen("code.in", "r", stdin);
     
    cin >> n >> m;
    s = n+m;
    build();
    for(int i = m; i< s; ++i){
        update(i, 1);
        cur[i - m + 1] = i;
    }
 
    int ind = m -1;
    for(int i =1; i<=m; ++i){
        int x;
        cin >> x;
        int ans = query(0, cur[x]) + 1;
         
        cout << ans << " ";
        update(cur[x], -1);
        update(ind, 1);
        cur[x] = ind--;
    }
     
     
    return 0;
}
